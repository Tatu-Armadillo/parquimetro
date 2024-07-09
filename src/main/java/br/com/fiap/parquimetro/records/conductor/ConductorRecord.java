package br.com.fiap.parquimetro.records.conductor;

import br.com.fiap.parquimetro.model.Conductor;

public record ConductorRecord(
        String name,
        String cpf,
        String phone,
        String email,
        AddressRecord address) {

    public static Conductor toEntity(final ConductorRecord record) {
        final var entity = new Conductor();
        entity.setName(record.name);
        entity.setCpf(record.cpf);
        entity.setPhone(record.phone);
        entity.setEmail(record.email);
        entity.setAddress(AddressRecord.toEntity(record.address));
        return entity;
    }

    public static ConductorRecord toRecord(final Conductor entity) {
        return new ConductorRecord(
                entity.getName(),
                entity.getCpf(),
                entity.getPhone(),
                entity.getEmail(),
                AddressRecord.toRecord(entity.getAddress()));
    }

}
