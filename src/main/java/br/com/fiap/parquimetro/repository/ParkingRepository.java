package br.com.fiap.parquimetro.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.fiap.parquimetro.model.Parking;

@Repository
public interface ParkingRepository extends JpaRepository<Parking, Long>{
    
}
