package br.com.fiap.parquimetro.controller;

import br.com.fiap.parquimetro.dto.MongoParkingDTO;
import br.com.fiap.parquimetro.model.MongoParking;
import br.com.fiap.parquimetro.service.MongoParkingService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value= "/tickets")
public class TicketController {

    @Autowired
    private MongoParkingService mongoParkingService;

    @GetMapping
    @PreAuthorize("hasRole('CONDUCTOR')")
    @SecurityRequirement(name = "bearer-key")
    public List<MongoParking> getAll(){
        return this.mongoParkingService.getAll();
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('CONDUCTOR')")
    @SecurityRequirement(name = "bearer-key")
    public MongoParking getById(@PathVariable String id){
        return this.mongoParkingService.getById(id);
    }

    @PostMapping
    @PreAuthorize("hasRole('CONDUCTOR')")
    @SecurityRequirement(name = "bearer-key")
    public MongoParking create(@RequestBody MongoParkingDTO mongoParkingDTO){
        MongoParking insertObject = new MongoParking(mongoParkingDTO);
        return this.mongoParkingService.create(insertObject);
    }

    @DeleteMapping
    @PreAuthorize("hasRole('CONDUCTOR')")
    @SecurityRequirement(name = "bearer-key")
    public void deleteAllDocumentsInParquimetro(){
        this.mongoParkingService.deleteAllDocumentsInParquimetro();
    }


}
