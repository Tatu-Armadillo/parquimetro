package br.com.fiap.parquimetro.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
public class MongoParkingDTO {
    private LocalDateTime createDate;

    private Long idPayment;

    private LocalDateTime timeStart;

    private Long idVehicle;

    private Long idEstablishment;

    public LocalDateTime getCreateDate() {
        return createDate;
    }

    public Long getIdPayment() {
        return idPayment;
    }

    public LocalDateTime getTimeStart() {
        return timeStart;
    }

    public Long getIdVehicle() {
        return idVehicle;
    }

    public Long getIdEstablishment() {
        return idEstablishment;
    }
}
