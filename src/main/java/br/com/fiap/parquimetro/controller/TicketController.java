package br.com.fiap.parquimetro.controller;

import br.com.fiap.parquimetro.model.FixedPeriodTicket;
import br.com.fiap.parquimetro.repository.TicketRepository;
import br.com.fiap.parquimetro.service.FixedPeriodTicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value= "/tickets")
public class TicketController {

    @Autowired
    private FixedPeriodTicketService fixedPeriodTicketService;

    @GetMapping
    public List<FixedPeriodTicket> getAll(){
        return this.fixedPeriodTicketService.getAll();
    }

    @GetMapping("/{id}")
    public FixedPeriodTicket getById(@PathVariable String id){
        return this.fixedPeriodTicketService.getById(id);
    }

    @PostMapping
    public FixedPeriodTicket create(@RequestBody FixedPeriodTicket fixedPeriodTicket){
        return this.fixedPeriodTicketService.create(fixedPeriodTicket);
    }
}
