package br.com.fiap.parquimetro.dto;

import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
public class MongoParkingDTO {
    private Long idPayment;

    private Long idVehicle;

    private Long idEstablishment;

    @Email
    private String userEmail;

    public @Email String getUserEmail() {
        return userEmail;
    }

    public Long getIdPayment() {
        return idPayment;
    }

    public Long getIdVehicle() {
        return idVehicle;
    }

    public Long getIdEstablishment() {
        return idEstablishment;
    }
}
