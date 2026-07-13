package com.afroditigkotsi.maritimefpaplatform.entity;

import com.afroditigkotsi.maritimefpaplatform.enums.BudgetStatus;
import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table(
        name = "budgets",
        uniqueConstraints = {
                @UniqueConstraint(
                        columnNames = {"vessel_id", "budgetYear"}
                )
        }
)
public class Budget extends BaseEntity {

    @Column(nullable = false)
    private Integer budgetYear;

    @Column(nullable = false)
    private BigDecimal fuelCost;

    @Column(nullable = false)
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
    private BigDecimal totalBudget;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private BudgetStatus status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "vessel_id", nullable = false)
    private Vessel vessel;

    public Budget() {
    }

    // Getters & Setters

    public Integer getBudgetYear() {
        return budgetYear;
    }

    public void setBudgetYear(Integer budgetYear) {
        this.budgetYear = budgetYear;
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

    public BigDecimal getTotalBudget() {
        return totalBudget;
    }

    public void setTotalBudget(BigDecimal totalBudget) {
        this.totalBudget = totalBudget;
    }

    public BudgetStatus getStatus() {
        return status;
    }

    public void setStatus(BudgetStatus status) {
        this.status = status;
    }

    public Vessel getVessel() {
        return vessel;
    }

    public void setVessel(Vessel vessel) {
        this.vessel = vessel;
    }
}