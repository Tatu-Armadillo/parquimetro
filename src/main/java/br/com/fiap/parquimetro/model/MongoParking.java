package br.com.fiap.parquimetro.model;

import br.com.fiap.parquimetro.dto.MongoParkingDTO;
import jakarta.validation.constraints.Email;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.time.LocalDateTime;

@Getter
@Setter
@Document
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MongoParking {

    @Id
    private String idParking;

    private boolean isActive;

    private LocalDateTime createDate;

    private LocalDateTime nextNotificationTimeScheduled;

    private Long idPayment;

    @Email
    private String userEmail;

    private String idVehicle;

    private Long idEstablishment;

    public LocalDateTime getNextNotificationTimeScheduled() {
        return nextNotificationTimeScheduled;
    }

    public LocalDateTime getCreateDate() {
        return createDate;
    }

    public String getIdParking() {
        return idParking;
    }

    public @Email String getUserEmail() {
        return userEmail;
    }

    public MongoParking(MongoParkingDTO mongoParkingDTO){
        this.idEstablishment = mongoParkingDTO.getIdEstablishment();
        this.idPayment = mongoParkingDTO.getIdPayment();
        this.idVehicle = mongoParkingDTO.getIdVehicle();
        this.userEmail = mongoParkingDTO.getUserEmail();
        this.createDate = LocalDateTime.now();
        this.nextNotificationTimeScheduled = LocalDateTime.now().plusHours(1);
        this.isActive = true;
    }

}
