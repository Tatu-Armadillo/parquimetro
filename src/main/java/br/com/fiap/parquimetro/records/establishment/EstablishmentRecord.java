package br.com.fiap.parquimetro.records.establishment;

import java.math.BigDecimal;

import br.com.fiap.parquimetro.model.Establishment;
import br.com.fiap.parquimetro.records.conductor.AddressRecord;

public record EstablishmentRecord(
        String name,
        BigDecimal priceHour,
        AddressRecord address) {

    public static Establishment toEntity(final EstablishmentRecord record) {
        final var entity = new Establishment();
        entity.setName(record.name);
        entity.setPriceHour(record.priceHour);
        entity.setAddress(AddressRecord.toEntity(record.address));
        return entity;
    }

    public static EstablishmentRecord toRecord(final Establishment entity) {
        return new EstablishmentRecord(
                entity.getName(),
                entity.getPriceHour(),
                AddressRecord.toRecord(entity.getAddress()));
    }

}