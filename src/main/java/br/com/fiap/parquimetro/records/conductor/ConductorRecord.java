package br.com.fiap.parquimetro.records.conductor;

import br.com.fiap.parquimetro.model.Conductor;
import br.com.fiap.parquimetro.model.enuns.PaymentFormat;

public record ConductorRecord(
        String name,
        String cpf,
        String phone,
        String email,
        PaymentFormat paymentFormat,
        AddressRecord address) {

    public static Conductor toEntity(final ConductorRecord record) {
        final var entity = new Conductor();
        entity.setName(record.name);
        entity.setCpf(record.cpf);
        entity.setPhone(record.phone);
        entity.setEmail(record.email);
        entity.setPaymentFormat(record.paymentFormat);
        entity.setAddress(AddressRecord.toEntity(record.address));
        return entity;
    }

    public static ConductorRecord toRecord(final Conductor entity) {
        return new ConductorRecord(
                entity.getName(),
                entity.getCpf(),
                entity.getPhone(),
                entity.getEmail(),
                entity.getPaymentFormat(),
                AddressRecord.toRecord(entity.getAddress()));
    }

}
