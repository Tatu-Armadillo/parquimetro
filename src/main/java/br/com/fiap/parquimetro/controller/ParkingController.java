package br.com.fiap.parquimetro.controller;

import br.com.fiap.parquimetro.dto.MongoParkingDTO;
import br.com.fiap.parquimetro.model.MongoParking;
import br.com.fiap.parquimetro.records.vehicle.VehicleRecord;
import br.com.fiap.parquimetro.service.MongoParkingService;
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

    @Autowired
    private MongoParkingService mongoParkingService;

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

    @PatchMapping("/closed")
    @PreAuthorize("hasRole('OWNER_ESTABLISHMENT')")
    @Transactional
    @SecurityRequirement(name = "bearer-key")
    @Operation(summary = "Closed Parking", description = "Closed parking with uncertain hours ", tags = {
            "Parking" }, responses = {
                    @ApiResponse(description = "Create", responseCode = "200", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ParkingRecord.class))),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content)
            })
    public ResponseEntity<ResponseBase<ParkingRecord>> closedUncertainHours(
            @RequestHeader("Authorization") final String token,
            @RequestParam(name = "licensePlate", defaultValue = "") final String licensePlate) {
        final var response = this.parkingService.closedParking(token, licensePlate);
        final var base = ResponseBase.of(ParkingRecord.toRecord(response));
        return ResponseEntity.ok(base);
    }

    @PostMapping("/uncertain_hours")
    @PreAuthorize("hasRole('CONDUCTOR')")
    @SecurityRequirement(name = "bearer-key")
    @Operation(summary = "Create uncertain hour ticket", responses = {
            @ApiResponse(description = "Create", responseCode = "200", content = @Content(mediaType = "application/json", schema = @Schema(implementation = MongoParking.class))),
            @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
            @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
            @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content)
    })
    public MongoParking createUncertainParking(@RequestBody MongoParkingDTO mongoParkingDTO){
        MongoParking insertObject = new MongoParking(mongoParkingDTO);
        return this.mongoParkingService.create(insertObject);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('CONDUCTOR')")
    @SecurityRequirement(name = "bearer-key")
    @Operation(summary = "Retrieve uncertain hour ticket by ID", responses = {
            @ApiResponse(description = "Success", responseCode = "200", content = @Content(mediaType = "application/json", schema = @Schema(implementation = MongoParking.class))),
            @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
            @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
            @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content)
    })
    public MongoParking getById(@PathVariable String id){
        // TODO: Insert User ID on JWT token and retrieve parking which is associated with them.
        return this.mongoParkingService.getById(id);
    }

    @PatchMapping("/{id}")
    @PreAuthorize("hasRole('CONDUCTOR')")
    @SecurityRequirement(name = "bearer-key")
    @Operation(summary = "Turn a specific parking with uncertain into inactive by ID")
    public void turnIncertainParkingIntoInactive(@PathVariable String id){
        // TODO: Send the receipt to the user
        this.mongoParkingService.turnIncertainParkingIntoInactive(id);
    }


    }


