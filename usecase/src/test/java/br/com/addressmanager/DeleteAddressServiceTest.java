package br.com.addressmanager;

import br.com.addressmanager.builder.AddressPersistenceAdapterBuilder;
import br.com.addressmanager.builder.AddressServiceBuilder;
import br.com.addressmanager.model.Address;
import org.junit.Test;

public class DeleteAddressServiceTest {

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowExceptionWhenAddressIdIsNull() {
        //given
        AddressService addressService = new AddressService(null, null, null);

        //when
        addressService.delete(null);
    }

    @Test
    public void shouldDeleteSuccessfully() {
        //given
        Address address = new Address(); address.setId(1L);
        AddressPersistenceAdapter addressPersistenceAdapter = AddressPersistenceAdapterBuilder.buildAddressPersistenceAdapter();
        addressPersistenceAdapter.save(address);

        AddressService addressService = AddressServiceBuilder.build(null, addressPersistenceAdapter, null);

        //when
        addressService.delete(1L);

        //then
        assert !addressPersistenceAdapter.findOne(1L).isPresent();
    }
}
