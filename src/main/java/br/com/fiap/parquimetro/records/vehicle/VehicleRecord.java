package br.com.fiap.parquimetro.records.vehicle;

import java.time.Year;

import br.com.fiap.parquimetro.model.Vehicle;
import br.com.fiap.parquimetro.model.enuns.FuelType;

public record VehicleRecord(
        String licensePlate,
        String chassis,
        Integer manufacturingYear,
        Integer modelYear,
        String brand,
        String model,
        String color,
        String fuel) {


        public static Vehicle toEntity(final VehicleRecord record) {
            final var entity = new Vehicle();
            entity.setLicensePlate(record.licensePlate);
            entity.setChassis(record.chassis);
            entity.setManufacturingYear(Year.of(record.manufacturingYear));
            entity.setModelYear(Year.of(record.modelYear));
            entity.setBrand(record.brand);
            entity.setModel(record.model);
            entity.setColor(record.color);
            entity.setFuel(FuelType.valueOf(record.fuel));
            return entity;
        }

        public static VehicleRecord toRecord(final Vehicle entity) {
            return new VehicleRecord(
                entity.getLicensePlate(),
                entity.getChassis(),
                entity.getManufacturingYear().getValue(), 
                entity.getModelYear().getValue(), 
                entity.getBrand(), 
                entity.getModel(), 
                entity.getColor(), 
                entity.getFuel().name());
        }
}
