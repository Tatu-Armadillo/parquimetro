package br.com.fiap.parquimetro.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import br.com.fiap.parquimetro.configuration.web.response.ResponseBase;
import br.com.fiap.parquimetro.records.parking.ParkingRecord;
import br.com.fiap.parquimetro.records.parking.ResumeParkingRecord;
import br.com.fiap.parquimetro.service.ParkingService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.*;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.transaction.Transactional;

@RestController
@RequestMapping("/parking")
@Tag(name = "Parking", description = "Endpoints for Managing Parkings")
public class ParkingController {

    private final ParkingService parkingService;

    @Autowired
    public ParkingController(ParkingService parkingService) {
        this.parkingService = parkingService;
    }

    @PostMapping
    @PreAuthorize("hasRole('CONDUCTOR')")
    @Transactional
    @SecurityRequirement(name = "bearer-key")
    @Operation(summary = "Create new Parking", description = "Init hours in parking", tags = {
            "Parking" }, responses = {
                    @ApiResponse(description = "Create", responseCode = "200", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ParkingRecord.class))),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content)
            })
    public ResponseEntity<ResponseBase<ParkingRecord>> initHoursParking(@RequestBody final ResumeParkingRecord record) {
        final var response = this.parkingService.initHoursParking(ResumeParkingRecord.toEntity(record));
        final var base = ResponseBase.of(ParkingRecord.toRecord(response));
        return ResponseEntity.ok(base);
    }

}
