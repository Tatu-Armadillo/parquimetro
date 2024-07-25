package br.com.fiap.parquimetro.service;
import br.com.fiap.parquimetro.model.MongoParking;
import br.com.fiap.parquimetro.repository.MongoParkingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class MongoParkingService {

    private final MongoTemplate mongoTemplate;

    @Autowired
    private MessageService messageService;

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

    public List<MongoParking> getUnnotifiedParkingLots(){
        Query query = new Query(Criteria.where("nextNotificaionTimeScheduled").lt(LocalDateTime.now())
                .and("isActive").is(true));
        return mongoTemplate.find(query, MongoParking.class);
    }

    public void deleteAllDocumentsInParquimetro() {
        for (String collectionName : mongoTemplate.getCollectionNames()) {
            mongoTemplate.remove(new Query(), "mongoParking");
        }
    }

    public MongoParking turnIncertainParkingIntoInactive(String id){
        Query query = new Query(Criteria.where("idParking").is(id));
        Update update = new Update().set("isActive", false);
        return mongoTemplate.findAndModify(query, update, MongoParking.class);
    }

    public void addOneHourToNextNotificationTime(String id) {
        MongoParking parking = mongoParkingRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Parking not found!"));
        LocalDateTime newTime = parking.getNextNotificationTimeScheduled().plusHours(1);

        Query query = new Query(Criteria.where("id").is(id));
        Update update = new Update().set("nextNotificationTimeScheduled", newTime);
        mongoTemplate.updateFirst(query, update, MongoParking.class);
    }

    @Scheduled(fixedRate = 300000) // 5 minutes
    public void checkForRenewalParking(){
        System.out.println("Verifying parking's to send renewal notification");
        for (MongoParking unnotifiedParkingLot :this.getUnnotifiedParkingLots()){
            messageService.sendSimpleEmailMessage(unnotifiedParkingLot.getUserEmail(),
                    "Parking time renewal",
                    "Your parking is getting renewal for 1 hour");
            this.addOneHourToNextNotificationTime(unnotifiedParkingLot.getIdParking());
        }
    }



}
