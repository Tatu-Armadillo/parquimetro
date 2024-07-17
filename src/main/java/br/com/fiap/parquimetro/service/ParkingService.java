package br.com.fiap.parquimetro.service;

import java.time.Duration;
import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.fiap.parquimetro.exception.BusinessException;
import br.com.fiap.parquimetro.model.Parking;
import br.com.fiap.parquimetro.model.enuns.PaymentFormat;
import br.com.fiap.parquimetro.repository.ParkingRepository;

@Service
public class ParkingService {

    private final ParkingRepository parkingRepository;
    private final VehicleService vehicleService;
    private final EstablishmentService establishmentService;

    @Autowired
    public ParkingService(
            final ParkingRepository parkingRepository,
            final VehicleService vehicleService,
            final EstablishmentService establishmentService) {
        this.parkingRepository = parkingRepository;
        this.vehicleService = vehicleService;
        this.establishmentService = establishmentService;
    }

    public Parking initHoursParking(final Parking parking) {
        final var vehicle = this.vehicleService.findVehicleByLicensePlate(parking.getVehicle().getLicensePlate());
        parking.setVehicle(vehicle);

        final var establishment = this.establishmentService
                .findEstablishmentByCnpj(parking.getEstablishment().getCnpj());
        parking.setEstablishment(establishment);

        if (parking.isTimeFixed() && parking.getVehicle().getConductor().getPaymentFormat().equals(PaymentFormat.PIX)) {
            this.fixedHours(parking);
        } else {
            this.uncertainHours(parking);
        }

        return this.parkingRepository.save(parking);
    }

    private void uncertainHours(final Parking parking) {
        parking.setTimeStart(LocalDateTime.now());
        parking.setTimeEnd(null);
    }

    private void fixedHours(final Parking parking) {
        if (parking.getTimeStart() == null && parking.getTimeEnd() == null) {
            throw new BusinessException(
                    "m=fixedHours Not possible save fixed hours with StartHours = null or EndHours = null");
        }
        final var totalHours = Duration.between(parking.getTimeStart(), parking.getTimeEnd());
        parking.setTotalTime(totalHours.toMinutes());
    }

}
