package br.com.addressmanager.builder;

import br.com.addressmanager.AddressJPA;
import br.com.addressmanager.model.Address;

public class AddressBuilder {

    public static Address build(AddressJPA addressJPA) {
        Address address = new Address();
        address.setId(addressJPA.getId());
        address.setCep(addressJPA.getCep());
        address.setCity(addressJPA.getCity());
        address.setComplement(addressJPA.getComplement());
        address.setNeighborhood(addressJPA.getNeighborhood());
        address.setNumber(addressJPA.getNumber());
        address.setState(addressJPA.getState());
        address.setStreet(addressJPA.getStreet());
        return address;
    }

    public static void merge(Address address, AddressJPA addressJPA) {
        address.setId(addressJPA.getId());
        address.setCep(addressJPA.getCep());
        address.setCity(addressJPA.getCity());
        address.setComplement(addressJPA.getComplement());
        address.setNeighborhood(addressJPA.getNeighborhood());
        address.setNumber(addressJPA.getNumber());
        address.setState(addressJPA.getState());
        address.setStreet(addressJPA.getStreet());
    }
}
