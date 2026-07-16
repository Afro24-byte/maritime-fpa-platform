package com.afroditigkotsi.maritimefpaplatform.service;

import com.afroditigkotsi.maritimefpaplatform.entity.Actual;
import com.afroditigkotsi.maritimefpaplatform.entity.Vessel;
import com.afroditigkotsi.maritimefpaplatform.repository.ActualRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
public class ActualService {

    private final ActualRepository actualRepository;

    public ActualService(ActualRepository actualRepository) {
        this.actualRepository = actualRepository;
    }

    public List<Actual> findAll() {
        return actualRepository.findAll();
    }

    public Optional<Actual> findById(Long id) {
        return actualRepository.findById(id);
    }

    public List<Actual> findByVessel(Vessel vessel) {
        return actualRepository.findByVessel(vessel);
    }

    public Actual save(Actual actual) {

        calculateTotalActual(actual);

        return actualRepository.save(actual);
    }

    public void deleteById(Long id) {
        actualRepository.deleteById(id);
    }

    public boolean existsByVesselAndActualYear(
            Vessel vessel,
            Integer actualYear) {

        return actualRepository.existsByVesselAndActualYear(
                vessel,
                actualYear
        );
    }

    public boolean existsByVesselAndActualYearAndIdNot(
            Vessel vessel,
            Integer actualYear,
            Long id) {

        return actualRepository.existsByVesselAndActualYearAndIdNot(
                vessel,
                actualYear,
                id
        );
    }

    private void calculateTotalActual(Actual actual) {

        BigDecimal total =
                safeValue(actual.getFuelCost())
                        .add(safeValue(actual.getCrewCost()))
                        .add(safeValue(actual.getMaintenanceCost()))
                        .add(safeValue(actual.getInsuranceCost()))
                        .add(safeValue(actual.getPortCharges()))
                        .add(safeValue(actual.getOtherExpenses()));

        actual.setTotalActual(total);
    }

    private BigDecimal safeValue(BigDecimal value) {
        return value == null ? BigDecimal.ZERO : value;
    }

}
