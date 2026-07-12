package com.afroditigkotsi.maritimefpaplatform.service;

import com.afroditigkotsi.maritimefpaplatform.entity.Fleet;
import com.afroditigkotsi.maritimefpaplatform.repository.FleetRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FleetService {

    private final FleetRepository fleetRepository;

    public FleetService(FleetRepository fleetRepository) {
        this.fleetRepository = fleetRepository;
    }

    public List<Fleet> findAll() {
        return fleetRepository.findAll();
    }

    public Optional<Fleet> findById(Long id) {
        return fleetRepository.findById(id);
    }

    public Fleet save(Fleet fleet) {
        return fleetRepository.save(fleet);
    }

    public void deleteById(Long id) {
        fleetRepository.deleteById(id);
    }
}
