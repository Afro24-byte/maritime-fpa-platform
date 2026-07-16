package com.afroditigkotsi.maritimefpaplatform.service;

import com.afroditigkotsi.maritimefpaplatform.entity.Actual;
import com.afroditigkotsi.maritimefpaplatform.entity.Budget;

import java.math.BigDecimal;

public class VarianceResult {

    private Budget budget;

    private Actual actual;

    private BigDecimal fuelVariance;

    private BigDecimal crewVariance;

    private BigDecimal maintenanceVariance;

    private BigDecimal insuranceVariance;

    private BigDecimal portVariance;

    private BigDecimal otherVariance;

    private BigDecimal totalVariance;

    private BigDecimal fuelVariancePercentage;

    private BigDecimal crewVariancePercentage;

    private BigDecimal maintenanceVariancePercentage;

    private BigDecimal insuranceVariancePercentage;

    private BigDecimal portVariancePercentage;

    private BigDecimal otherVariancePercentage;

    private BigDecimal totalVariancePercentage;

    public VarianceResult() {
    }

    public Budget getBudget() {
        return budget;
    }

    public void setBudget(Budget budget) {
        this.budget = budget;
    }

    public Actual getActual() {
        return actual;
    }

    public void setActual(Actual actual) {
        this.actual = actual;
    }

    public BigDecimal getFuelVariance() {
        return fuelVariance;
    }

    public void setFuelVariance(BigDecimal fuelVariance) {
        this.fuelVariance = fuelVariance;
    }

    public BigDecimal getCrewVariance() {
        return crewVariance;
    }

    public void setCrewVariance(BigDecimal crewVariance) {
        this.crewVariance = crewVariance;
    }

    public BigDecimal getMaintenanceVariance() {
        return maintenanceVariance;
    }

    public void setMaintenanceVariance(BigDecimal maintenanceVariance) {
        this.maintenanceVariance = maintenanceVariance;
    }

    public BigDecimal getInsuranceVariance() {
        return insuranceVariance;
    }

    public void setInsuranceVariance(BigDecimal insuranceVariance) {
        this.insuranceVariance = insuranceVariance;
    }

    public BigDecimal getPortVariance() {
        return portVariance;
    }

    public void setPortVariance(BigDecimal portVariance) {
        this.portVariance = portVariance;
    }

    public BigDecimal getOtherVariance() {
        return otherVariance;
    }

    public void setOtherVariance(BigDecimal otherVariance) {
        this.otherVariance = otherVariance;
    }

    public BigDecimal getTotalVariance() {
        return totalVariance;
    }

    public void setTotalVariance(BigDecimal totalVariance) {
        this.totalVariance = totalVariance;
    }

    public BigDecimal getFuelVariancePercentage() {
        return fuelVariancePercentage;
    }

    public void setFuelVariancePercentage(BigDecimal fuelVariancePercentage) {
        this.fuelVariancePercentage = fuelVariancePercentage;
    }

    public BigDecimal getCrewVariancePercentage() {
        return crewVariancePercentage;
    }

    public void setCrewVariancePercentage(BigDecimal crewVariancePercentage) {
        this.crewVariancePercentage = crewVariancePercentage;
    }

    public BigDecimal getMaintenanceVariancePercentage() {
        return maintenanceVariancePercentage;
    }

    public void setMaintenanceVariancePercentage(BigDecimal maintenanceVariancePercentage) {
        this.maintenanceVariancePercentage = maintenanceVariancePercentage;
    }

    public BigDecimal getInsuranceVariancePercentage() {
        return insuranceVariancePercentage;
    }

    public void setInsuranceVariancePercentage(BigDecimal insuranceVariancePercentage) {
        this.insuranceVariancePercentage = insuranceVariancePercentage;
    }

    public BigDecimal getPortVariancePercentage() {
        return portVariancePercentage;
    }

    public void setPortVariancePercentage(BigDecimal portVariancePercentage) {
        this.portVariancePercentage = portVariancePercentage;
    }

    public BigDecimal getOtherVariancePercentage() {
        return otherVariancePercentage;
    }

    public void setOtherVariancePercentage(BigDecimal otherVariancePercentage) {
        this.otherVariancePercentage = otherVariancePercentage;
    }

    public BigDecimal getTotalVariancePercentage() {
        return totalVariancePercentage;
    }

    public void setTotalVariancePercentage(BigDecimal totalVariancePercentage) {
        this.totalVariancePercentage = totalVariancePercentage;
    }
}
