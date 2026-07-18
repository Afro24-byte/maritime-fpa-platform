package com.afroditigkotsi.maritimefpaplatform.service;

import com.afroditigkotsi.maritimefpaplatform.enums.ScenarioType;

import java.math.BigDecimal;

public class ForecastScenarioResult {

    private ScenarioType scenarioType;

    private BigDecimal totalForecast;

    public ScenarioType getScenarioType() {
        return scenarioType;
    }

    public void setScenarioType(ScenarioType scenarioType) {
        this.scenarioType = scenarioType;
    }

    public BigDecimal getTotalForecast() {
        return totalForecast;
    }

    public void setTotalForecast(BigDecimal totalForecast) {
        this.totalForecast = totalForecast;
    }
}
