package br.com.fiap.parquimetro.controller;

import br.com.fiap.parquimetro.model.Payment;
import br.com.fiap.parquimetro.service.EstablishmentService;
import br.com.fiap.parquimetro.service.ParkingService;
import br.com.fiap.parquimetro.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/payments")
public class PaymentController {
    private final PaymentService paymentService;
    private final ParkingService parkingService;
    private final EstablishmentService establishmentService;

    @Autowired
    public PaymentController(PaymentService paymentService, ParkingService parkingService, EstablishmentService establishmentService) {
        this.paymentService = paymentService;
        this.parkingService = parkingService;
        this.establishmentService = establishmentService;
    }

    @PostMapping("/process")
    public ResponseEntity<Payment> processPayment(@RequestParam String licensePlate, @RequestParam String token) {
        var establishment = establishmentService.getEstablishmentByToken(token);
        var parking = parkingService.findParkingByVehiclelicensePlate(licensePlate, establishment.getCnpj());

        Payment payment = paymentService.processPayment(parking);
        return ResponseEntity.ok(payment);
    }
}
