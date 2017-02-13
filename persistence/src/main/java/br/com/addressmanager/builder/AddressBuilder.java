package br.com.addressmanager.builder;

import br.com.addressmanager.AddressJPA;
import br.com.addressmanager.model.Address;

public class AddressBuilder {

    public static Address build(AddressJPA addressJPA) {
        Address address = new Address();
        address.setId(addressJPA.getId());
        address.setCep(addressJPA.getCep());
        address.setCity(addressJPA.getCity());
        address.setUserId(addressJPA.getUserId());
        address.setComplement(addressJPA.getComplement());
        address.setNeighborhood(addressJPA.getNeighborhood());
        address.setNumber(addressJPA.getNumber());
        address.setState(addressJPA.getState());
        address.setStreet(addressJPA.getStreet());
        return address;
    }

    public static void mergeAddressToAddressJPA(Address address, AddressJPA addressJPA) {
        addressJPA.setId(address.getId());
        addressJPA.setNumber(address.getNumber());
        addressJPA.setCep(address.getCep());
        addressJPA.setCity(address.getCity());
        addressJPA.setUserId(address.getUserId());
        addressJPA.setComplement(address.getComplement());
        addressJPA.setNeighborhood(address.getNeighborhood());
        addressJPA.setState(address.getState());
        addressJPA.setStreet(address.getStreet());
    }
}
