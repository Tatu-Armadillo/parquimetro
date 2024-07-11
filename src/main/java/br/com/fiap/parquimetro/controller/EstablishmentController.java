package br.com.fiap.parquimetro.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.fiap.parquimetro.configuration.web.response.ResponseBase;
import br.com.fiap.parquimetro.records.establishment.EstablishmentRecord;
import br.com.fiap.parquimetro.service.EstablishmentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.transaction.Transactional;

@RestController
@RequestMapping("/establishment")
@Tag(name = "Establishment", description = "Endpoints for Managing establishments")
public class EstablishmentController {

    private final EstablishmentService establishmentService;

    public EstablishmentController(final EstablishmentService establishmentService) {
        this.establishmentService = establishmentService;
    }

    @PostMapping
    @PreAuthorize("hasRole('OWNER_ESTABLISHMENT')")
    @Transactional
    @SecurityRequirement(name = "bearer-key")
    @Operation(summary = "Create new establishment", description = "Save information by establishment with owener", tags = {
            "Establishment" }, responses = {
                    @ApiResponse(description = "Create", responseCode = "200", content = @Content(mediaType = "application/json", schema = @Schema(implementation = EstablishmentRecord.class))),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content)
            })
    public ResponseEntity<ResponseBase<EstablishmentRecord>> save(
            @RequestHeader("Authorization") final String token,
            @RequestBody final EstablishmentRecord record) {
        final var response = this.establishmentService.save(EstablishmentRecord.toEntity(record), token);
        final var base = ResponseBase.of(EstablishmentRecord.toRecord(response));
        return ResponseEntity.ok(base);
    }

}
