package br.com.fiap.parquimetro.service.job;

import java.time.LocalDateTime;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import br.com.fiap.parquimetro.service.MessageService;
import br.com.fiap.parquimetro.service.ParkingService;

@Service
public class ExpirationParkingJob {

    private static final Logger logger = LoggerFactory.getLogger(ExpirationParkingJob.class);

    private final ParkingService parkingService;
    private final MessageService messageService;

    @Autowired
    public ExpirationParkingJob(final ParkingService parkingService, MessageService messageService) {
        this.parkingService = parkingService;
        this.messageService = messageService;
    }

    // @Scheduled(fixedRate = 30000) // 30000 ms = 30 seconds
    @Scheduled(fixedRate = 7200000) // 7200000 ms = 2 hours
    public void checkExpirations() {
        final var parkingExpirations = this.parkingService.findParkingsWithExpirationDate(LocalDateTime.now());
        parkingExpirations.forEach(p -> {
            this.messageService.sendSimpleEmailMessage(
                    p.getVehicle().getConductor().getEmail(),
                    "Exceed time Parking",
                    "Your vehicle's parking time will exceed the " + p.getTimeEnd());
        });
        logger.info("m=checkExpirations Success to send messages. Total=" + parkingExpirations.size());
    }

}
