package com.afroditigkotsi.maritimefpaplatform.repository;

import com.afroditigkotsi.maritimefpaplatform.entity.Budget;
import com.afroditigkotsi.maritimefpaplatform.entity.Forecast;
import com.afroditigkotsi.maritimefpaplatform.entity.Vessel;
import com.afroditigkotsi.maritimefpaplatform.enums.ScenarioType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ForecastRepository extends JpaRepository<Forecast, Long> {

    List<Forecast> findByVessel(Vessel vessel);

    List<Forecast> findByBudget(Budget budget);

    boolean existsByBudgetAndScenarioType(
            Budget budget,
            ScenarioType scenarioType);

    boolean existsByBudgetAndScenarioTypeAndIdNot(
            Budget budget,
            ScenarioType scenarioType,
            Long id);

    List<Forecast> findByBudgetBudgetYear(Integer budgetYear);
}
