package br.com.fiap.parquimetro.records.payment;

import java.math.BigDecimal;

import br.com.fiap.parquimetro.model.Payment;
import br.com.fiap.parquimetro.records.parking.ParkingRecord;

public record PaymentRecord(
    String paymentFormat,
    BigDecimal totalPrice,
    ParkingRecord parking) {

    public Payment toEntity(final PaymentRecord record) {
        final var entity = new Payment();
        entity.setPaymentFormat(record.paymentFormat);
        entity.setTotalPrice(record.totalPrice);
        entity.setParking(ParkingRecord.toEntity(record.parking));
        return entity;
    }

    public PaymentRecord toRecord(final Payment entity) {
        return new PaymentRecord(paymentFormat, totalPrice, parking);
    }
    
}
