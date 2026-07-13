package com.afroditigkotsi.maritimefpaplatform.entity;

import com.afroditigkotsi.maritimefpaplatform.enums.ForecastStatus;
import com.afroditigkotsi.maritimefpaplatform.enums.ScenarioType;
import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table(name = "forecasts")
public class Forecast extends BaseEntity {

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ScenarioType scenarioType;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ForecastStatus status;

    @Column(nullable = false)
    private Integer operatingDays;

    @Column(nullable = false)
    private Integer offHireDays;

    @Column(nullable = false)
    private BigDecimal dailyRevenueRate;

    @Column(nullable = false)
    private BigDecimal fuelCost;

    @Column(nullable =false)
    private BigDecimal crewCost;

    @Column(nullable = false)
    private BigDecimal maintenanceCost;

    @Column(nullable = false)
    private BigDecimal insuranceCost;

    @Column(nullable = false)
    private BigDecimal portCharges;

    @Column(nullable = false)
    private BigDecimal otherExpenses;

    @Column(nullable = false)
    private BigDecimal totalForecast;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "vessel_id", nullable = false)
    private Vessel vessel;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "budget_id", nullable = false)
    private Budget budget;

    public Forecast() {
    }

    public ScenarioType getScenarioType() {
        return scenarioType;
    }

    public void setScenarioType(ScenarioType scenarioType) {
        this.scenarioType = scenarioType;
    }

    public ForecastStatus getStatus() {
        return status;
    }

    public void setStatus(ForecastStatus status) {
        this.status = status;
    }

    public Integer getOperatingDays() {
        return operatingDays;
    }

    public void setOperatingDays(Integer operatingDays) {
        this.operatingDays = operatingDays;
    }

    public Integer getOffHireDays() {
        return offHireDays;
    }

    public void setOffHireDays(Integer offHireDays) {
        this.offHireDays = offHireDays;
    }

    public BigDecimal getDailyRevenueRate() {
        return dailyRevenueRate;
    }

    public void setDailyRevenueRate(BigDecimal dailyRevenueRate) {
        this.dailyRevenueRate = dailyRevenueRate;
    }

    public BigDecimal getFuelCost() {
        return fuelCost;
    }

    public void setFuelCost(BigDecimal fuelCost) {
        this.fuelCost = fuelCost;
    }

    public BigDecimal getCrewCost() {
        return crewCost;
    }

    public void setCrewCost(BigDecimal crewCost) {
        this.crewCost = crewCost;
    }

    public BigDecimal getMaintenanceCost() {
        return maintenanceCost;
    }

    public void setMaintenanceCost(BigDecimal maintenanceCost) {
        this.maintenanceCost = maintenanceCost;
    }

    public BigDecimal getInsuranceCost() {
        return insuranceCost;
    }

    public void setInsuranceCost(BigDecimal insuranceCost) {
        this.insuranceCost = insuranceCost;
    }

    public BigDecimal getPortCharges() {
        return portCharges;
    }

    public void setPortCharges(BigDecimal portCharges) {
        this.portCharges = portCharges;
    }

    public BigDecimal getOtherExpenses() {
        return otherExpenses;
    }

    public void setOtherExpenses(BigDecimal otherExpenses) {
        this.otherExpenses = otherExpenses;
    }

    public BigDecimal getTotalForecast() {
        return totalForecast;
    }

    public void setTotalForecast(BigDecimal totalForecast) {
        this.totalForecast = totalForecast;
    }

    public Vessel getVessel() {
        return vessel;
    }

    public void setVessel(Vessel vessel) {
        this.vessel = vessel;
    }

    public Budget getBudget() {
        return budget;
    }

    public void setBudget(Budget budget) {
        this.budget = budget;
    }
}
