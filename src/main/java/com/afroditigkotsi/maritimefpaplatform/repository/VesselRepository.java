package com.afroditigkotsi.maritimefpaplatform.repository;

import com.afroditigkotsi.maritimefpaplatform.entity.Vessel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface VesselRepository extends JpaRepository<Vessel, Long> {

    Optional<Vessel> findByImoNumber(String imoNumber);

    boolean existsByImoNumber(String imoNumber);

}
