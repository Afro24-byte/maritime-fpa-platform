package com.afroditigkotsi.maritimefpaplatform.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class KpiDashboardResult {

    private Integer year;

    private long totalFleets;

    private long activeFleets;

    private long totalVessels;

    private long activeVessels;

    private BigDecimal totalBudget;

    private BigDecimal totalForecast;

    private BigDecimal totalActual;

    private BigDecimal totalVariance;

    private Double totalVariancePercentage;

    private List<VesselPerformanceResult> vesselPerformance =
            new ArrayList<>();

    private List<ForecastScenarioResult> forecastScenarios =
            new ArrayList<>();

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public long getTotalFleets() {
        return totalFleets;
    }

    public void setTotalFleets(long totalFleets) {
        this.totalFleets = totalFleets;
    }

    public long getActiveFleets() {
        return activeFleets;
    }

    public void setActiveFleets(long activeFleets) {
        this.activeFleets = activeFleets;
    }

    public long getTotalVessels() {
        return totalVessels;
    }

    public void setTotalVessels(long totalVessels) {
        this.totalVessels = totalVessels;
    }

    public long getActiveVessels() {
        return activeVessels;
    }

    public void setActiveVessels(long activeVessels) {
        this.activeVessels = activeVessels;
    }

    public BigDecimal getTotalBudget() {
        return totalBudget;
    }

    public void setTotalBudget(BigDecimal totalBudget) {
        this.totalBudget = totalBudget;
    }

    public BigDecimal getTotalForecast() {
        return totalForecast;
    }

    public void setTotalForecast(BigDecimal totalForecast) {
        this.totalForecast = totalForecast;
    }

    public BigDecimal getTotalActual() {
        return totalActual;
    }

    public void setTotalActual(BigDecimal totalActual) {
        this.totalActual = totalActual;
    }

    public BigDecimal getTotalVariance() {
        return totalVariance;
    }

    public void setTotalVariance(BigDecimal totalVariance) {
        this.totalVariance = totalVariance;
    }

    public Double getTotalVariancePercentage() {
        return totalVariancePercentage;
    }

    public void setTotalVariancePercentage(Double totalVariancePercentage) {
        this.totalVariancePercentage = totalVariancePercentage;
    }

    public List<VesselPerformanceResult> getVesselPerformance() {
        return vesselPerformance;
    }

    public void setVesselPerformance(
            List<VesselPerformanceResult> vesselPerformance) {
        this.vesselPerformance = vesselPerformance;
    }

    public List<ForecastScenarioResult> getForecastScenarios() {
        return forecastScenarios;
    }

    public void setForecastScenarios(
            List<ForecastScenarioResult> forecastScenarios) {
        this.forecastScenarios = forecastScenarios;
    }
}
