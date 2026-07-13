package com.afroditigkotsi.maritimefpaplatform.service;

import com.afroditigkotsi.maritimefpaplatform.entity.Budget;
import com.afroditigkotsi.maritimefpaplatform.entity.Forecast;
import com.afroditigkotsi.maritimefpaplatform.entity.Vessel;
import com.afroditigkotsi.maritimefpaplatform.enums.ScenarioType;
import com.afroditigkotsi.maritimefpaplatform.repository.ForecastRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
public class ForecastService {

    private final ForecastRepository forecastRepository;

    public ForecastService(ForecastRepository forecastRepository) {
        this.forecastRepository = forecastRepository;
    }

    public List<Forecast> findAll() {
        return forecastRepository.findAll();
    }

    public Optional<Forecast> findById(Long id) {
        return forecastRepository.findById(id);
    }

    public List<Forecast> findByVessel(Vessel vessel) {
        return forecastRepository.findByVessel(vessel);
    }

    public List<Forecast> findByBudget(Budget budget) {
        return forecastRepository.findByBudget(budget);
    }

    public Forecast save(Forecast forecast) {

        validateVesselMatchesBudget(forecast);
        calculateTotalForecast(forecast);

        return forecastRepository.save(forecast);
    }

    public void deleteById(Long id) {
        forecastRepository.deleteById(id);
    }

    public boolean existsByBudgetAndScenarioType(
            Budget budget,
            ScenarioType scenarioType) {

        return forecastRepository.existsByBudgetAndScenarioType(
                budget,
                scenarioType
        );
    }

    public boolean existsByBudgetAndScenarioTypeAndIdNot(
            Budget budget,
            ScenarioType scenarioType,
            Long id) {

        return forecastRepository
                .existsByBudgetAndScenarioTypeAndIdNot(
                        budget,
                        scenarioType,
                        id
                );
    }

    private void validateVesselMatchesBudget(Forecast forecast) {

        if (forecast.getVessel() == null) {
            throw new IllegalArgumentException(
                    "A vessel must be selected."
            );
        }

        if (forecast.getBudget() == null) {
            throw new IllegalArgumentException(
                    "A budget must be selected."
            );
        }

        Vessel forecastVessel = forecast.getVessel();
        Vessel budgetVessel = forecast.getBudget().getVessel();

        if (budgetVessel == null
                || forecastVessel.getId() == null
                || budgetVessel.getId() == null
                || !forecastVessel.getId().equals(budgetVessel.getId())) {

            throw new IllegalArgumentException(
                    "Forecast and Budget must belong to the same Vessel."
            );
        }
    }

    private void calculateTotalForecast(Forecast forecast) {

        BigDecimal total =
                safeValue(forecast.getFuelCost())
                        .add(safeValue(forecast.getCrewCost()))
                        .add(safeValue(forecast.getMaintenanceCost()))
                        .add(safeValue(forecast.getInsuranceCost()))
                        .add(safeValue(forecast.getPortCharges()))
                        .add(safeValue(forecast.getOtherExpenses()));

        forecast.setTotalForecast(total);
    }

    private BigDecimal safeValue(BigDecimal value) {
        return value == null ? BigDecimal.ZERO : value;
    }
}
