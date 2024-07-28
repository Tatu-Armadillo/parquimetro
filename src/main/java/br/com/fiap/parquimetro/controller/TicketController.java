package br.com.fiap.parquimetro.controller;

import br.com.fiap.parquimetro.dto.MongoParkingDTO;
import br.com.fiap.parquimetro.model.MongoParking;
import br.com.fiap.parquimetro.records.parking.ParkingRecord;
import br.com.fiap.parquimetro.service.MongoParkingService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//@RestController
//@RequestMapping(value= "/tickets")
//@Tag(name = "Tickets (Mongo Implementation)", description = "Administrative management resources")
public class TicketController {

    @Autowired
    private MongoParkingService mongoParkingService;

    @GetMapping
    @PreAuthorize("hasRole('ADMINISTRATOR')")
    @SecurityRequirement(name = "bearer-key")
    @Operation(summary = "Retrieve all tickets")
    public List<MongoParking> getAll(){
        return this.mongoParkingService.getAll();
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ADMINISTRATOR')")
    @SecurityRequirement(name = "bearer-key")
    @Operation(summary = "Retrieve all tickets")
    public MongoParking getById(@PathVariable String id){
        return this.mongoParkingService.getById(id);
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMINISTRATOR')")
    @SecurityRequirement(name = "bearer-key")
    public MongoParking create(@RequestBody MongoParkingDTO mongoParkingDTO){
        MongoParking insertObject = new MongoParking(mongoParkingDTO);
        return this.mongoParkingService.create(insertObject);
    }

    @DeleteMapping
    @PreAuthorize("hasRole('ADMINISTRATOR')")
    @SecurityRequirement(name = "bearer-key")
    public void deleteAllDocumentsInParquimetro(){
        this.mongoParkingService.deleteAllDocumentsInParquimetro();
    }


}
