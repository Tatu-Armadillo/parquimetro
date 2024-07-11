package br.com.fiap.parquimetro.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.fiap.parquimetro.model.Establishment;
import br.com.fiap.parquimetro.repository.EstablishmentRepository;

@Service
public class EstablishmentService {

    private final EstablishmentRepository establishmentRepository;
    private final UserService userService;
    private final AddressService addressService;

    @Autowired
    public EstablishmentService(
            final EstablishmentRepository establishmentRepository,
            final UserService userService,
            final AddressService addressService) {
        this.establishmentRepository = establishmentRepository;
        this.userService = userService;
        this.addressService = addressService;
    }

    public Establishment save(final Establishment establishment, final String token) {
        final var user = this.userService.findUserByToken(token);
        establishment.setUser(user);
        final var address = this.addressService.save(establishment.getAddress());
        establishment.setAddress(address);
        return this.establishmentRepository.save(establishment);
    }

}
