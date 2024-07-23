package br.com.fiap.parquimetro.dto;

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
