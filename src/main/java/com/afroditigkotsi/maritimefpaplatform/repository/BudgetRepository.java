package com.afroditigkotsi.maritimefpaplatform.repository;

import com.afroditigkotsi.maritimefpaplatform.entity.Budget;
import com.afroditigkotsi.maritimefpaplatform.entity.Vessel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BudgetRepository extends JpaRepository<Budget, Long> {

    List<Budget> findByVessel(Vessel vessel);

    Optional<Budget> findByVesselAndBudgetYear(Vessel vessel, Integer budgetYear);

    boolean existsByVesselAndBudgetYear(Vessel vessel, Integer budgetYear);

    boolean existsByVesselAndBudgetYearAndIdNot(
            Vessel vessel,
            Integer budgetYear,
            Long id
    );
}
