package br.com.fiap.parquimetro.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.fiap.parquimetro.model.Vehicle;
import br.com.fiap.parquimetro.repository.VehicleRepository;

@Service
public class VehicleService {

    private final VehicleRepository vehicleRepository;
    private final ConductorService conductorService;

    public VehicleService(final VehicleRepository vehicleRepository, final ConductorService conductorService) {
        this.vehicleRepository = vehicleRepository;
        this.conductorService = conductorService;
    }

    public Vehicle save(final Vehicle entity, final String token) {
        final var conductor = this.conductorService.getConductorByToken(token);
        entity.setConductor(conductor);
        return this.vehicleRepository.save(entity);
    }

    public Page<Vehicle> findVehiclesByConductor(final Pageable pageable, final String token) {
        final var conductor = this.conductorService.getConductorByToken(token);
        return this.vehicleRepository.findVehiclesByConductor(pageable, conductor);
    }

}
