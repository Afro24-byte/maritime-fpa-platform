package com.afroditigkotsi.maritimefpaplatform.entity;

import com.afroditigkotsi.maritimefpaplatform.enums.FuelType;
import com.afroditigkotsi.maritimefpaplatform.enums.VesselStatus;
import com.afroditigkotsi.maritimefpaplatform.enums.VesselType;
import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table(name = "vessels")
public class Vessel extends BaseEntity {

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, unique = true)
    private String imoNumber;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private VesselType vesselType;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private VesselStatus vesselStatus;

    @Column(nullable = false)
    private Integer buildYear;

    @Column(nullable = false, precision = 12, scale = 2)
    private BigDecimal deadweightTonnage;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private FuelType fuelType;

    @Column(nullable = false)
    private String flagCountry;

    @Column(nullable = false)
    private boolean active = true;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fleet_id", nullable = false)
    private Fleet fleet;


    public Vessel() {
    }

    public Vessel(String name,
                  String imoNumber,
                  VesselType vesselType,
                  VesselStatus vesselStatus,
                  Integer buildYear,
                  BigDecimal deadweightTonnage,
                  FuelType fuelType,
                  String flagCountry,
                  boolean active,
                  Fleet fleet) {

        this.name = name;
        this.imoNumber = imoNumber;
        this.vesselType = vesselType;
        this.vesselStatus = vesselStatus;
        this.buildYear = buildYear;
        this.deadweightTonnage = deadweightTonnage;
        this.fuelType = fuelType;
        this.flagCountry = flagCountry;
        this.active = active;
        this.fleet = fleet;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImoNumber() {
        return imoNumber;
    }

    public void setImoNumber(String imoNumber) {
        this.imoNumber = imoNumber;
    }

    public VesselType getVesselType() {
        return vesselType;
    }

    public void setVesselType(VesselType vesselType) {
        this.vesselType = vesselType;
    }

    public VesselStatus getVesselStatus() {
        return vesselStatus;
    }

    public void setVesselStatus(VesselStatus vesselStatus) {
        this.vesselStatus = vesselStatus;
    }

    public Integer getBuildYear() {
        return buildYear;
    }

    public void setBuildYear(Integer buildYear) {
        this.buildYear = buildYear;
    }

    public BigDecimal getDeadweightTonnage() {
        return deadweightTonnage;
    }

    public void setDeadweightTonnage(BigDecimal deadweightTonnage) {
        this.deadweightTonnage = deadweightTonnage;
    }

    public FuelType getFuelType() {
        return fuelType;
    }

    public void setFuelType(FuelType fuelType) {
        this.fuelType = fuelType;
    }

    public String getFlagCountry() {
        return flagCountry;
    }

    public void setFlagCountry(String flagCountry) {
        this.flagCountry = flagCountry;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public Fleet getFleet() {
        return fleet;
    }

    public void setFleet(Fleet fleet) {
        this.fleet = fleet;
    }

}
