package br.com.fiap.parquimetro.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.fiap.parquimetro.model.Conductor;
import br.com.fiap.parquimetro.repository.ConductorRepository;

@Service
public class ConductorService {

    private final ConductorRepository conductorRepository;
    private final UserService userService;
    private final AddressService addressService;

    @Autowired
    public ConductorService(
            final ConductorRepository conductorRepository,
            final AddressService addressService,
            final UserService userService) {
        this.conductorRepository = conductorRepository;
        this.userService = userService;
        this.addressService = addressService;
    }

    public Conductor save(final Conductor conductor, final String token) {
        final var user = this.userService.findUserByToken(token);
        conductor.setUser(user);
        final var address = this.addressService.save(conductor.getAddress());
        conductor.setAddress(address);
        return this.conductorRepository.save(conductor);
    }

}
