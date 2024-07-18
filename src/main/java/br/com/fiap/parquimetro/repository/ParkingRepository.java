package br.com.fiap.parquimetro.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.fiap.parquimetro.model.Parking;

@Repository
public interface ParkingRepository extends JpaRepository<Parking, Long> {

        @Query(" SELECT parking FROM Parking parking "
                        + " INNER JOIN parking.vehicle vehicle "
                        + " INNER JOIN parking.establishment establishment "
                        + " WHERE vehicle.licensePlate = :licensePlate "
                        + " AND establishment.cnpj = :cnpj "
                        + " ORDER BY parking.createDate DESC "
                        + " LIMIT 1 ")
        Optional<Parking> findParkingByVehicleAndEstablishment(
                        @Param("licensePlate") String licensePlate,
                        @Param("cnpj") String cnpj);

}
