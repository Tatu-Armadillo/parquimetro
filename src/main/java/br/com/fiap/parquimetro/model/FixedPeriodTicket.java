package br.com.fiap.parquimetro.model;

import jakarta.validation.constraints.Email;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Duration;
import java.time.LocalDateTime;

@Document
@Data
public class FixedPeriodTicket {
    @Id
    private String id;

    private String licensePlate;

    private LocalDateTime startDate;

    private Duration period;

    private Float fare;

    private Email userEmail;
}
