package br.com.fiap.parquimetro.service;

import br.com.fiap.parquimetro.exception.BusinessException;
import br.com.fiap.parquimetro.model.Parking;
import br.com.fiap.parquimetro.model.Payment;
import br.com.fiap.parquimetro.model.enuns.PaymentFormat;
import br.com.fiap.parquimetro.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalDateTime;

@Service
public class PaymentService {

    private final PaymentRepository paymentRepository;

    @Autowired
    public PaymentService(PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }

    public Payment processPayment(final Parking parking) {
        final var paymentFormat = parking.getVehicle().getConductor().getPaymentFormat();

        if (!isValidPaymentFormat(paymentFormat, parking.isTimeFixed())) {
            throw new BusinessException("Forma de pagamento não permitida para o tipo de estacionamento.");
        }

        final BigDecimal totalPrice = parking.isTimeFixed() ? calculateFixedPrice(parking) : calculateVariablePrice(parking);

        final var payment = new Payment();
        payment.setPaymentFormat(paymentFormat.toString());
        payment.setTotalPrice(totalPrice);
        payment.setPaymentDate(LocalDateTime.now());
        payment.setParking(parking);
        payment.setPaymentCompleted(false);

        return paymentRepository.save(payment);
    }

    private boolean isValidPaymentFormat(PaymentFormat paymentFormat, boolean isTimeFixed) {
        if (isTimeFixed) {
            return paymentFormat == PaymentFormat.CREDIT_CARD ||
                    paymentFormat == PaymentFormat.DEBIT_CARD ||
                    paymentFormat == PaymentFormat.PIX;
        } else {
            return paymentFormat == PaymentFormat.CREDIT_CARD ||
                    paymentFormat == PaymentFormat.DEBIT_CARD;
        }
    }

    private BigDecimal calculateFixedPrice(final Parking parking) {
        return parking.getEstablishment().getPriceHour();
    }

    private BigDecimal calculateVariablePrice(final Parking parking) {
        final long hours = Duration.between(parking.getTimeStart(), parking.getTimeEnd()).toHours();
        final BigDecimal hourlyRate = parking.getEstablishment().getPriceHour();
        return hourlyRate.multiply(BigDecimal.valueOf(hours));
    }

}