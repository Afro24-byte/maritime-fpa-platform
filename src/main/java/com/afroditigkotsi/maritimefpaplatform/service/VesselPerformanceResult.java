package com.afroditigkotsi.maritimefpaplatform.service;

import java.math.BigDecimal;
import com.afroditigkotsi.maritimefpaplatform.enums.PerformanceStatus;

public class VesselPerformanceResult {

    private String vesselName;

    private BigDecimal budget;

    private BigDecimal actual;

    private BigDecimal variance;

    private Double variancePercentage;

    private PerformanceStatus performanceStatus;

    public String getVesselName() {
        return vesselName;
    }

    public void setVesselName(String vesselName) {
        this.vesselName = vesselName;
    }

    public BigDecimal getBudget() {
        return budget;
    }

    public void setBudget(BigDecimal budget) {
        this.budget = budget;
    }

    public BigDecimal getActual() {
        return actual;
    }

    public void setActual(BigDecimal actual) {
        this.actual = actual;
    }

    public BigDecimal getVariance() {
        return variance;
    }

    public void setVariance(BigDecimal variance) {
        this.variance = variance;
    }

    public Double getVariancePercentage() {
        return variancePercentage;
    }

    public void setVariancePercentage(Double variancePercentage) {
        this.variancePercentage = variancePercentage;
    }

    public PerformanceStatus getPerformanceStatus() {
        return performanceStatus;
    }

    public void setPerformanceStatus(PerformanceStatus performanceStatus) {
        this.performanceStatus = performanceStatus;
    }
}
