package com.afroditigkotsi.maritimefpaplatform.repository;

import com.afroditigkotsi.maritimefpaplatform.entity.Actual;
import com.afroditigkotsi.maritimefpaplatform.entity.Vessel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ActualRepository extends JpaRepository<Actual, Long> {

    List<Actual> findByVessel(Vessel vessel);

    Optional<Actual> findByVesselAndActualYear(
            Vessel vessel,
            Integer actualYear
    );

    boolean existsByVesselAndActualYear(
            Vessel vessel,
            Integer actualYear
    );

    boolean existsByVesselAndActualYearAndIdNot(
            Vessel vessel,
            Integer actualYear,
            Long id
    );


}
