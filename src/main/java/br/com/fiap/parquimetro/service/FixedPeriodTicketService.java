package br.com.fiap.parquimetro.service;
import br.com.fiap.parquimetro.model.FixedPeriodTicket;
import br.com.fiap.parquimetro.repository.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FixedPeriodTicketService {

    @Autowired
    private TicketRepository ticketRepository;

    public List<FixedPeriodTicket> getAll() {
        return this.ticketRepository.findAll();
    }

    public FixedPeriodTicket getById(String id){
        return this.ticketRepository
                .findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Ticket not found!"));
    }

    public FixedPeriodTicket create(FixedPeriodTicket fixedPeriodTicket){
        return this.ticketRepository.save(fixedPeriodTicket);
    }


}
