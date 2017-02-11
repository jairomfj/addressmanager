package br.com.addressmanager.builder;

import br.com.addressmanager.AddressJPA;
import br.com.addressmanager.model.Address;

public class AddressJPABuilder {

    public static AddressJPA build(Address address) {
        AddressJPA addressJPA = new AddressJPA();
        addressJPA.setId(address.getId());
        addressJPA.setCep(address.getCep());
        addressJPA.setCity(address.getCity());
        addressJPA.setComplement(address.getComplement());
        addressJPA.setNeighborhood(address.getNeighborhood());
        addressJPA.setNumber(address.getNumber());
        addressJPA.setState(address.getState());
        addressJPA.setStreet(address.getStreet());
        return addressJPA;
    }
}
