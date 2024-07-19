package br.com.fiap.parquimetro.model;

import br.com.fiap.parquimetro.dto.MongoParkingDTO;
import lombok.AllArgsConstructor;
import org.springframework.data.annotation.Id;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;
import java.time.LocalDateTime;

@Document
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MongoParking {

    @Id
    private String idParking;

    private boolean isActive;

    private LocalDateTime createDate;

    private boolean isRenewalNotificationSent;

    private LocalDateTime nextNotificaionTimeScheduled;

    private Long idPayment;

    private LocalDateTime timeStart;

    private Long idVehicle;

    private Long idEstablishment;

    public MongoParking(MongoParkingDTO mongoParkingDTO){
        this.createDate = mongoParkingDTO.getCreateDate();
        this.idEstablishment = mongoParkingDTO.getIdEstablishment();
        this.idPayment = mongoParkingDTO.getIdPayment();
        this.idVehicle = mongoParkingDTO.getIdVehicle();
        this.timeStart = mongoParkingDTO.getTimeStart();
        this.isRenewalNotificationSent = false;
        this.nextNotificaionTimeScheduled = LocalDateTime.now().plusHours(1);
        this.isActive = true;
    }

}
