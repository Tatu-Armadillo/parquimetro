package br.com.fiap.parquimetro.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.fiap.parquimetro.model.Conductor;

@Repository
public interface ConductorRepository extends JpaRepository<Conductor, Long> {

    @Query(" SELECT conductor FROM Conductor conductor "
            + " INNER JOIN conductor.user user "
            + " WHERE user.userName = :username ")
    Optional<Conductor> getConductorByToken(@Param("username") String username);

}
