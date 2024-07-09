package br.com.fiap.parquimetro.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.fiap.parquimetro.exception.BusinessException;
import br.com.fiap.parquimetro.model.Address;
import br.com.fiap.parquimetro.repository.AddressRepository;

@Service
public class AddressService {

    private final AddressRepository addressRepository;

    @Autowired
    public AddressService(final AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }

    public Address findAddressByCep(final String cep) {
        return this.addressRepository.findAddressByCep(cep)
                .orElseThrow(() -> new BusinessException(
                        "m=AddressService.findAddressByCep - Address not found with CEP: " + cep));
    }

    public Address save(final Address address) {
        return this.addressRepository.findAddressByCep(address.getCep())
                .orElseGet(() -> this.addressRepository.save(address));
    }

}
