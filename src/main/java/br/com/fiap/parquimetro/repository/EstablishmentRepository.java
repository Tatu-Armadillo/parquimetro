package br.com.fiap.parquimetro.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.fiap.parquimetro.model.Establishment;

@Repository
public interface EstablishmentRepository extends JpaRepository<Establishment, Long> {

    Optional<Establishment> findEstablishmentByCnpj(String cnpj);

    @Query(" SELECT establishment FROM Establishment establishment "
            + " INNER JOIN establishment.user user "
            + " WHERE user.userName = :username ")
    Optional<Establishment> getEstablishmentByToken(@Param("username") String username);

}
