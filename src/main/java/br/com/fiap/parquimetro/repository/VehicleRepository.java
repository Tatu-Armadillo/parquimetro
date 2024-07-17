package br.com.fiap.parquimetro.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.fiap.parquimetro.model.Conductor;
import br.com.fiap.parquimetro.model.Vehicle;

@Repository
public interface VehicleRepository extends JpaRepository<Vehicle, Long> {

    Page<Vehicle> findVehiclesByConductor(Pageable pageable, Conductor conductor);

    Optional<Vehicle> findVehicleByLicensePlate(String licensePlate);

}
