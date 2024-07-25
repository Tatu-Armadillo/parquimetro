package br.com.fiap.parquimetro.service.job;

import br.com.fiap.parquimetro.model.MongoParking;
import br.com.fiap.parquimetro.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;

@Service
public class ReceiptConstructor {

    @Autowired
    private MessageService messageService;

    public static String formatDuration(Duration duration) {
        long hours = duration.toHours();
        long minutes = duration.toMinutes() % 60;
        long seconds = duration.getSeconds() % 60;

        return String.format("%d horas, %d minutos e %d segundos", hours, minutes, seconds);
    }

    public static String calculateFare(LocalDateTime past, float multiplier) {
        LocalDateTime now = LocalDateTime.now();
        Duration duration = Duration.between(past, now);
        long hours = duration.toHours();
        float result = hours * multiplier;

        return String.valueOf(result);
    }

    public void sendUncertainParkingReceipt(MongoParking mongoParking){
        // TODO: Fixed fare, needs adjustments
        StringBuilder emailBody = new StringBuilder();
        emailBody.append("Olá, aqui está seu recibo: /nTempo total estacionado: ")
                .append(formatDuration(Duration.between(mongoParking.getCreateDate(), LocalDateTime.now())))
                .append("/nTarifa aplicada de R$5,00/nValor total pago: ")
                .append(calculateFare(mongoParking.getCreateDate(),5.00f));

        this.messageService.sendSimpleEmailMessage(
                mongoParking.getUserEmail(),
                "Recibo estacionamento",
                emailBody.toString()
        );
    }

}
