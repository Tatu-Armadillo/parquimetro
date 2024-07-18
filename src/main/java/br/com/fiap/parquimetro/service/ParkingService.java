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

        this.verifyVehicleIsParking(parking.getVehicle().getLicensePlate(), parking.getEstablishment().getCnpj());

        final var vehicle = this.vehicleService.findVehicleByLicensePlate(parking.getVehicle().getLicensePlate());
        parking.setVehicle(vehicle);

        final var establishment = this.establishmentService
                .findEstablishmentByCnpj(parking.getEstablishment().getCnpj());
        parking.setEstablishment(establishment);

        if (parking.isTimeFixed()) {
            this.fixedHours(parking);
        } else {
            this.uncertainHours(parking);
        }

        parking.setCreateDate(LocalDateTime.now());
        return this.parkingRepository.save(parking);
    }

    public Parking closedParking(final String token, final String licensePlate) {
        final var establishment = this.establishmentService.getEstablishmentByToken(token);
        final var parking = this.findParkingByVehiclelicensePlate(licensePlate, establishment.getCnpj());

        parking.setTimeEnd(LocalDateTime.now());
        this.fixedHours(parking);

        return this.parkingRepository.save(parking);
    }

    private Parking findParkingByVehiclelicensePlate(final String licensePlate, final String cnpj) {
        return this.parkingRepository.findParkingByVehicleAndEstablishment(licensePlate, cnpj)
                .orElseThrow(() -> new BusinessException(
                        "m=findParkingByVehiclelicensePlate Not found Parking with vehicle = " + licensePlate));
    }

    private void uncertainHours(final Parking parking) {
        if (parking.getVehicle().getConductor().getPaymentFormat().equals(PaymentFormat.PIX)) {
            throw new BusinessException(
                    "m=uncertainHours Not possible create parking with uncertain hours e payment format = PIX");
        }
        parking.setTimeStart(LocalDateTime.now());
        parking.setTimeEnd(null);
    }

    private void fixedHours(final Parking parking) {
        if (parking.getTimeStart() == null && parking.getTimeEnd() == null) {
            throw new BusinessException(
                    "m=fixedHours Not possible save fixed hours with StartHours and/or EndHours nulls");
        }
        final var totalHours = Duration.between(parking.getTimeStart(), parking.getTimeEnd());
        parking.setTotalTime(totalHours.toMinutes());
    }

    private void verifyVehicleIsParking(final String licensePlate, final String cnpj) {
        final var parking = this.parkingRepository.findParkingByVehicleAndEstablishment(licensePlate, cnpj);
        if (parking.isPresent() && parking.get().getTimeEnd() == null) {
            throw new BusinessException("m=verifyVehicleIsParking Vehicle is parking now");
        }
    }

}
