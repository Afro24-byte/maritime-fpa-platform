package com.afroditigkotsi.maritimefpaplatform.service;

import com.afroditigkotsi.maritimefpaplatform.entity.Budget;
import com.afroditigkotsi.maritimefpaplatform.entity.Vessel;
import com.afroditigkotsi.maritimefpaplatform.repository.BudgetRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
public class BudgetService {

    private final BudgetRepository budgetRepository;

    public BudgetService(BudgetRepository budgetRepository) {
        this.budgetRepository = budgetRepository;
    }

    public List<Budget> findAll() {
        return budgetRepository.findAll();
    }

    public Optional<Budget> findById(Long id) {
        return budgetRepository.findById(id);
    }

    public List<Budget> findByVessel(Vessel vessel) {
        return budgetRepository.findByVessel(vessel);
    }

    public Budget save(Budget budget) {

        calculateTotalBudget(budget);

        return budgetRepository.save(budget);
    }

    public void deleteById(Long id) {
        budgetRepository.deleteById(id);
    }

    public boolean existsByVesselAndBudgetYear(Vessel vessel, Integer budgetYear) {
        return budgetRepository.existsByVesselAndBudgetYear(vessel, budgetYear);
    }

    private void calculateTotalBudget(Budget budget) {

        BigDecimal total =
                budget.getFuelCost()
                        .add(budget.getCrewCost())
                        .add(budget.getMaintenanceCost())
                        .add(budget.getInsuranceCost())
                        .add(budget.getPortCharges())
                        .add(budget.getOtherExpenses());

        budget.setTotalBudget(total);
    }
}
