package br.com.addressmanager.builder;

import br.com.addressmanager.AddressPersistenceAdapter;
import br.com.addressmanager.AddressService;
import br.com.addressmanager.CepServiceAdapter;
import br.com.addressmanager.model.AddressComparator;

public class AddressServiceBuilder {

    public static AddressService build(CepServiceAdapter cepServiceAdapter, AddressPersistenceAdapter addressPersistenceAdapter, AddressComparator addressComparator) {
        return new AddressService(cepServiceAdapter, addressPersistenceAdapter, addressComparator);
    }
}
