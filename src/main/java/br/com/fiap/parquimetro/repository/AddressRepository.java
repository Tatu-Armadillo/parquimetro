package br.com.fiap.parquimetro.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.fiap.parquimetro.model.Address;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {
    
    Optional<Address> findAddressByCep(String cep);

}
