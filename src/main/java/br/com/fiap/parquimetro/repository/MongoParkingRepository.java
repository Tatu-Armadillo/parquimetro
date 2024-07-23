package br.com.fiap.parquimetro.repository;

import br.com.fiap.parquimetro.model.MongoParking;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MongoParkingRepository extends MongoRepository<MongoParking, String> {

}
