package br.com.fiap.parquimetro.service;
import br.com.fiap.parquimetro.model.MongoParking;
import br.com.fiap.parquimetro.repository.MongoParkingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MongoParkingService {

    private final MongoTemplate mongoTemplate;

    public MongoParkingService (MongoTemplate mongoTemplate){
        this.mongoTemplate = mongoTemplate;
    }

    @Autowired
    private MongoParkingRepository mongoParkingRepository;

    public List<MongoParking> getAll() {
        return this.mongoParkingRepository.findAll();
    }

    public MongoParking getById(String id){
        return this.mongoParkingRepository
                .findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Ticket not found!"));
    }

    public MongoParking create(MongoParking mongoParking){
        return this.mongoParkingRepository.save(mongoParking);
    }

    public List<MongoParking> getAllWhereIsRenewalNotificationSentIsFalse(){
        Query query = new Query(Criteria.where("isRenewalNotificationSent").is(false));
        return mongoTemplate.find(query, MongoParking.class);
    }


}
