package br.com.fiap.parquimetro.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.fiap.parquimetro.configuration.web.response.ResponseBase;
import br.com.fiap.parquimetro.records.conductor.ConductorRecord;
import br.com.fiap.parquimetro.service.ConductorService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.*;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.transaction.Transactional;

@RestController
@RequestMapping("/conductor")
@Tag(name = "Conductor", description = "Endpoints for Managing conductors")
public class ConductorController {

    private final ConductorService conductorService;

    @Autowired
    public ConductorController(final ConductorService conductorService) {
        this.conductorService = conductorService;
    }

    @PostMapping
    @PreAuthorize("hasRole('CONDUCTOR')")
    @Transactional
    @SecurityRequirement(name = "bearer-key")
    @Operation(summary = "Create new Conductor", description = "Save information by conductor and address", tags = {
            "Conductor" }, responses = {
                    @ApiResponse(description = "Create", responseCode = "200", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ConductorRecord.class))),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content)
            })
    public ResponseEntity<ResponseBase<ConductorRecord>> save(
            @RequestHeader("Authorization") final String token,
            @RequestBody final ConductorRecord record) {
        final var response = this.conductorService.save(ConductorRecord.toEntity(record), token);
        final var base = ResponseBase.of(ConductorRecord.toRecord(response));
        return ResponseEntity.ok(base);
    }

}
