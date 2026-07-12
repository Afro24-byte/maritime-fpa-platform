package com.afroditigkotsi.maritimefpaplatform.service;

import com.afroditigkotsi.maritimefpaplatform.entity.Vessel;
import com.afroditigkotsi.maritimefpaplatform.repository.VesselRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VesselService {

    private final VesselRepository vesselRepository;

    public VesselService(VesselRepository vesselRepository) {
        this.vesselRepository = vesselRepository;
    }

    public List<Vessel> findAll() {
        return vesselRepository.findAll();
    }

    public Optional<Vessel> findById(Long id) {
        return vesselRepository.findById(id);
    }

    public Optional<Vessel> findByImoNumber(String imoNumber) {
        return vesselRepository.findByImoNumber(imoNumber);
    }

    public Vessel save(Vessel vessel) {

        Optional<Vessel> existingVessel =
                vesselRepository.findByImoNumber(vessel.getImoNumber());

        if (existingVessel.isPresent()
                && !existingVessel.get().getId().equals(vessel.getId())) {

            throw new IllegalArgumentException(
                    "A vessel with IMO number "
                            + vessel.getImoNumber()
                            + " already exists."
            );
        }

        return vesselRepository.save(vessel);
    }

    public void deleteById(Long id) {
        vesselRepository.deleteById(id);
    }
}
