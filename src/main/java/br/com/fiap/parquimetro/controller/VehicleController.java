package br.com.fiap.parquimetro.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.fiap.parquimetro.configuration.web.response.ResponseBase;
import br.com.fiap.parquimetro.configuration.web.response.ResponseBasePagination;
import br.com.fiap.parquimetro.records.vehicle.VehicleRecord;
import br.com.fiap.parquimetro.service.VehicleService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.*;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.transaction.Transactional;

@RestController
@RequestMapping("/vehicle")
@Tag(name = "Vehicle", description = "Endpoints for Managing Vehicle")
public class VehicleController {

    private final VehicleService vehicleService;

    @Autowired
    public VehicleController(final VehicleService vehicleService) {
        this.vehicleService = vehicleService;
    }

    @PostMapping
    @PreAuthorize("hasRole('CONDUCTOR')")
    @Transactional
    @SecurityRequirement(name = "bearer-key")
    @Operation(summary = "Create new Vehicle", description = "Save information by vehicle and your owner", tags = {
            "Vehicle" }, responses = {
                    @ApiResponse(description = "Create", responseCode = "200", content = @Content(mediaType = "application/json", schema = @Schema(implementation = VehicleRecord.class))),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content)
            })
    public ResponseEntity<ResponseBase<VehicleRecord>> save(
            @RequestHeader("Authorization") final String token,
            @RequestBody final VehicleRecord record) {
        final var response = this.vehicleService.save(VehicleRecord.toEntity(record), token);
        final var base = ResponseBase.of(VehicleRecord.toRecord(response));
        return ResponseEntity.ok(base);
    }

    @GetMapping
    @PreAuthorize("hasRole('CONDUCTOR')")
    @SecurityRequirement(name = "bearer-key")
    @Operation(summary = "Find Vehicles", description = "Find Vehicles linked Conductor", tags = {
            "Vehicle" }, responses = {
                    @ApiResponse(description = "Success", responseCode = "200", content = {
                            @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = VehicleRecord.class))) }),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content)
            })
    public ResponseEntity<ResponseBasePagination<List<VehicleRecord>>> findVehiclesByConductor(
            @PageableDefault(sort = "buyDate", direction = Direction.DESC) final Pageable pageable,
            @RequestHeader("Authorization") final String token) {
        final var response = this.vehicleService.findVehiclesByConductor(pageable, token);
        final var base = ResponseBasePagination.of(response.map(VehicleRecord::toRecord));
        return ResponseEntity.ok(base);
    }

}
