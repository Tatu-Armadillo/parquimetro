package br.com.fiap.parquimetro.repository;

import br.com.fiap.parquimetro.model.FixedPeriodTicket;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TicketRepository extends MongoRepository<FixedPeriodTicket, String> {


}
