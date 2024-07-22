package br.com.fiap.parquimetro.runners;

import br.com.fiap.parquimetro.model.MongoParking;
import br.com.fiap.parquimetro.service.MongoParkingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ParkingAlerts {
    @Autowired
    private MongoParkingService MongoParkingService;
}
