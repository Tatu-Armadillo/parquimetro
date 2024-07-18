package br.com.fiap.parquimetro.records.parking;

import java.time.LocalDateTime;

import br.com.fiap.parquimetro.model.Parking;
import br.com.fiap.parquimetro.records.establishment.EstablishmentRecord;
import br.com.fiap.parquimetro.records.vehicle.VehicleRecord;

public record ParkingRecord(
        boolean timeFixed,
        LocalDateTime timeStart,
        LocalDateTime timeEnd,
        VehicleRecord vehicle,
        EstablishmentRecord establishment) {

    public static Parking toEntity(final ParkingRecord record) {
        final var entity = new Parking();
        entity.setTimeFixed(record.timeFixed);
        entity.setTimeStart(record.timeStart);
        entity.setTimeEnd(record.timeEnd);
        entity.setVehicle(VehicleRecord.toEntity(record.vehicle));
        entity.setEstablishment(EstablishmentRecord.toEntity(record.establishment));
        return entity;
    }

    public static ParkingRecord toRecord(final Parking parking) {
        return new ParkingRecord(
                parking.isTimeFixed(),
                parking.getTimeStart(),
                parking.getTimeEnd(),
                VehicleRecord.toRecord(parking.getVehicle()),
                EstablishmentRecord.toRecord(parking.getEstablishment()));
    }

}
