package br.com.fiap.parquimetro.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.fiap.parquimetro.model.Establishment;

@Repository
public interface EstablishmentRepository extends JpaRepository<Establishment, Long> {

}