package com.afroditigkotsi.maritimefpaplatform.service;

import com.afroditigkotsi.maritimefpaplatform.repository.ActualRepository;
import com.afroditigkotsi.maritimefpaplatform.repository.BudgetRepository;
import com.afroditigkotsi.maritimefpaplatform.repository.ForecastRepository;
import org.springframework.stereotype.Service;

import com.afroditigkotsi.maritimefpaplatform.entity.Fleet;
import com.afroditigkotsi.maritimefpaplatform.entity.Vessel;
import com.afroditigkotsi.maritimefpaplatform.enums.VesselStatus;

import java.util.List;

import com.afroditigkotsi.maritimefpaplatform.entity.Actual;
import com.afroditigkotsi.maritimefpaplatform.entity.Budget;
import com.afroditigkotsi.maritimefpaplatform.entity.Forecast;
import com.afroditigkotsi.maritimefpaplatform.enums.ScenarioType;
import com.afroditigkotsi.maritimefpaplatform.enums.PerformanceStatus;


import java.math.RoundingMode;
import java.math.BigDecimal;

@Service
public class KpiAnalyticsService {

    private final FleetService fleetService;
    private final VesselService vesselService;
    private final BudgetRepository budgetRepository;
    private final ForecastRepository forecastRepository;
    private final ActualRepository actualRepository;


    public KpiAnalyticsService(
            FleetService fleetService,
            VesselService vesselService,
            BudgetRepository budgetRepository,
            ForecastRepository forecastRepository,
            ActualRepository actualRepository,
            VarianceService varianceService) {

        this.fleetService = fleetService;
        this.vesselService = vesselService;
        this.budgetRepository = budgetRepository;
        this.forecastRepository = forecastRepository;
        this.actualRepository = actualRepository;
    }

    public KpiDashboardResult buildDashboard(Integer year) {

        KpiDashboardResult result = new KpiDashboardResult();

        result.setYear(year);

        calculateOperationalKpis(result);

        calculateFinancialKpis(result, year);

        buildVesselPerformance(result, year);

        buildScenarioSummary(result, year);

        return result;
    }

    private void calculateOperationalKpis(
            KpiDashboardResult result) {

        List<Fleet> fleets = fleetService.findAll();
        List<Vessel> vessels = vesselService.findAll();

        result.setTotalFleets(fleets.size());

        result.setActiveFleets(
                fleets.stream()
                        .filter(Fleet::isActive)
                        .count());

        result.setTotalVessels(vessels.size());

        result.setActiveVessels(
                vessels.stream()
                        .filter(v -> v.getVesselStatus() == VesselStatus.ACTIVE)
                        .count());
    }

    private void calculateFinancialKpis(
            KpiDashboardResult result,
            Integer year) {

        List<Budget> budgets =
                budgetRepository.findByBudgetYear(year);

        List<Forecast> forecasts =
                forecastRepository.findByBudgetBudgetYear(year);

        List<Actual> actuals =
                actualRepository.findByActualYear(year);

        BigDecimal totalBudget = budgets.stream()
                .map(Budget::getTotalBudget)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        BigDecimal totalForecast = forecasts.stream()
                .filter(f -> f.getScenarioType() == ScenarioType.BASE)
                .map(Forecast::getTotalForecast)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        BigDecimal totalActual = actuals.stream()
                .map(Actual::getTotalActual)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        BigDecimal totalVariance =
                totalActual.subtract(totalBudget);

        result.setTotalBudget(totalBudget);
        result.setTotalForecast(totalForecast);
        result.setTotalActual(totalActual);
        result.setTotalVariance(totalVariance);

        if (totalBudget.compareTo(BigDecimal.ZERO) > 0) {

            double variancePercentage =
                    totalVariance
                            .multiply(BigDecimal.valueOf(100))
                            .divide(totalBudget, 2, RoundingMode.HALF_UP)
                            .doubleValue();

            result.setTotalVariancePercentage(
                    variancePercentage);

        } else {

            result.setTotalVariancePercentage(0.0);

        }
    }

    private void buildVesselPerformance(
            KpiDashboardResult result,
            Integer year) {

        List<Budget> budgets =
                budgetRepository.findByBudgetYear(year);

        for (Budget budget : budgets) {

            VesselPerformanceResult vesselResult =
                    new VesselPerformanceResult();

            vesselResult.setVesselName(
                    budget.getVessel().getName());

            vesselResult.setBudget(
                    budget.getTotalBudget());

            Actual actual = actualRepository
                    .findByVesselAndActualYear(
                            budget.getVessel(),
                            year)
                    .orElse(null);

            if (actual == null) {

                vesselResult.setPerformanceStatus(
                        PerformanceStatus.NO_DATA);

            } else {

                vesselResult.setActual(
                        actual.getTotalActual());

                BigDecimal variance =
                        actual.getTotalActual()
                                .subtract(
                                        budget.getTotalBudget());

                vesselResult.setVariance(variance);

                if (budget.getTotalBudget()
                        .compareTo(BigDecimal.ZERO) > 0) {

                    double variancePercentage =
                            variance
                                    .multiply(BigDecimal.valueOf(100))
                                    .divide(
                                            budget.getTotalBudget(),
                                            2,
                                            RoundingMode.HALF_UP)
                                    .doubleValue();

                    vesselResult.setVariancePercentage(
                            variancePercentage);
                }

                if (variance.compareTo(BigDecimal.ZERO) >= 0) {

                    vesselResult.setPerformanceStatus(
                            PerformanceStatus.FAVORABLE);

                } else {

                    vesselResult.setPerformanceStatus(
                            PerformanceStatus.UNFAVORABLE);
                }
            }

            result.getVesselPerformance()
                    .add(vesselResult);
        }
    }

    private void buildScenarioSummary(
            KpiDashboardResult result,
            Integer year) {

        List<Forecast> forecasts =
                forecastRepository.findByBudgetBudgetYear(year);

        for (ScenarioType scenario : ScenarioType.values()) {

            BigDecimal totalForecast = forecasts.stream()
                    .filter(f -> f.getScenarioType() == scenario)
                    .map(Forecast::getTotalForecast)
                    .reduce(BigDecimal.ZERO, BigDecimal::add);

            ForecastScenarioResult scenarioResult =
                    new ForecastScenarioResult();

            scenarioResult.setScenarioType(scenario);

            scenarioResult.setTotalForecast(totalForecast);

            result.getForecastScenarios()
                    .add(scenarioResult);
        }
    }

}
