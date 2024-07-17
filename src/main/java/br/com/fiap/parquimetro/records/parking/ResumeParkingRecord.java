package br.com.fiap.parquimetro.records.parking;

import java.time.LocalDateTime;

import br.com.fiap.parquimetro.model.Establishment;
import br.com.fiap.parquimetro.model.Parking;
import br.com.fiap.parquimetro.model.Vehicle;

public record ResumeParkingRecord(
        boolean timeFixed,
        LocalDateTime timeStart,
        LocalDateTime timeEnd,
        String licensePlate,
        String cnpj) {

    public static Parking toEntity(final ResumeParkingRecord record) {
        final var entity = new Parking();
        entity.setTimeFixed(record.timeFixed);
        entity.setTimeStart(record.timeStart);
        entity.setTimeEnd(record.timeEnd);
        final var vehicle = new Vehicle();
        vehicle.setLicensePlate(record.licensePlate);
        entity.setVehicle(vehicle);
        final var establishment = new Establishment();
        establishment.setCnpj(record.cnpj);
        entity.setEstablishment(establishment);
        return entity;
    }

    public static ResumeParkingRecord toRecord(final Parking parking) {
        return new ResumeParkingRecord(
                parking.isTimeFixed(),
                parking.getTimeStart(),
                parking.getTimeEnd(),
                parking.getVehicle().getLicensePlate(),
                parking.getEstablishment().getCnpj());
    }

}
