package com.afroditigkotsi.maritimefpaplatform.service;

import com.afroditigkotsi.maritimefpaplatform.entity.Actual;
import com.afroditigkotsi.maritimefpaplatform.entity.Budget;
import com.afroditigkotsi.maritimefpaplatform.entity.Vessel;
import com.afroditigkotsi.maritimefpaplatform.repository.ActualRepository;
import com.afroditigkotsi.maritimefpaplatform.repository.BudgetRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Service
public class VarianceService {

    private final BudgetRepository budgetRepository;
    private final ActualRepository actualRepository;

    public VarianceService(BudgetRepository budgetRepository,
                           ActualRepository actualRepository) {

        this.budgetRepository = budgetRepository;
        this.actualRepository = actualRepository;
    }

    public VarianceResult calculateVariance(
            Vessel vessel,
            Integer year) {

        Budget budget = budgetRepository
                .findByVesselAndBudgetYear(vessel, year)
                .orElseThrow(() ->
                        new IllegalArgumentException(
                                "Budget not found."
                        ));

        Actual actual = actualRepository
                .findByVesselAndActualYear(vessel, year)
                .orElseThrow(() ->
                        new IllegalArgumentException(
                                "Actual not found."
                        ));

        VarianceResult result = new VarianceResult();

        result.setBudget(budget);
        result.setActual(actual);

        result.setFuelVariance(
                actual.getFuelCost().subtract(budget.getFuelCost()));

        result.setCrewVariance(
                actual.getCrewCost().subtract(budget.getCrewCost()));

        result.setMaintenanceVariance(
                actual.getMaintenanceCost().subtract(budget.getMaintenanceCost()));

        result.setInsuranceVariance(
                actual.getInsuranceCost().subtract(budget.getInsuranceCost()));

        result.setPortVariance(
                actual.getPortCharges().subtract(budget.getPortCharges()));

        result.setOtherVariance(
                actual.getOtherExpenses().subtract(budget.getOtherExpenses()));

        result.setTotalVariance(
                actual.getTotalActual().subtract(budget.getTotalBudget()));

        result.setFuelVariancePercentage(
                calculatePercentage(
                        budget.getFuelCost(),
                        result.getFuelVariance()));

        result.setCrewVariancePercentage(
                calculatePercentage(
                        budget.getCrewCost(),
                        result.getCrewVariance()));

        result.setMaintenanceVariancePercentage(
                calculatePercentage(
                        budget.getMaintenanceCost(),
                        result.getMaintenanceVariance()));

        result.setInsuranceVariancePercentage(
                calculatePercentage(
                        budget.getInsuranceCost(),
                        result.getInsuranceVariance()));

        result.setPortVariancePercentage(
                calculatePercentage(
                        budget.getPortCharges(),
                        result.getPortVariance()));

        result.setOtherVariancePercentage(
                calculatePercentage(
                        budget.getOtherExpenses(),
                        result.getOtherVariance()));

        result.setTotalVariancePercentage(
                calculatePercentage(
                        budget.getTotalBudget(),
                        result.getTotalVariance()));

        return result;
    }

    private BigDecimal calculatePercentage(
            BigDecimal budget,
            BigDecimal variance) {

        if (budget == null || budget.compareTo(BigDecimal.ZERO) == 0) {
            return BigDecimal.ZERO;
        }

        return variance
                .divide(budget, 4, RoundingMode.HALF_UP)
                .multiply(BigDecimal.valueOf(100));
    }

}