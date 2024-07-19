package br.com.fiap.parquimetro.runners;

import br.com.fiap.parquimetro.model.MongoParking;
import br.com.fiap.parquimetro.repository.MongoParkingRepository;
import br.com.fiap.parquimetro.service.MongoParkingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ParkingAlerts {
    @Autowired
    private MongoParkingService MongoParkingService;

    public void checkForRenewalParking(){
        List<MongoParking> mongoParkingListToRenewalAlert = MongoParkingService.getAllWhereIsRenewalNotificationSentIsFalse();
        mongoParkingListToRenewalAlert.forEach(banana -> System.out.println("banana")); // TODO: Implementar lógica
    }
}
