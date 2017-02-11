package br.com.addressmanager;

import br.com.addressmanager.model.Address;

import java.util.Optional;

public interface AddressPersistenceAdapter {
    Optional<Address> findOne(Long id);
    void save(Address address);
    void update(Address address);
    void delete(Address address);
}
