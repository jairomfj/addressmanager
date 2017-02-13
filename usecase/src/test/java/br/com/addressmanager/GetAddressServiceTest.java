package br.com.addressmanager;

import br.com.addressmanager.builder.AddressPersistenceAdapterBuilder;
import br.com.addressmanager.builder.AddressServiceBuilder;
import br.com.addressmanager.model.Address;
import org.junit.Test;

public class GetAddressServiceTest {

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowExceptionWhenAddressIdIsNull() {
        //given
        AddressService addressService = new AddressService(null, null, null);

        //when
        addressService.get(null);
    }

    @Test
    public void shouldGetSuccessfully() {
        //given
        Address address = new Address(); address.setId(1L);
        AddressPersistenceAdapter addressPersistenceAdapter = AddressPersistenceAdapterBuilder.buildAddressPersistenceAdapter();
        addressPersistenceAdapter.save(address);

        AddressService addressService = AddressServiceBuilder.build(null, addressPersistenceAdapter, null);

        //when
        Address persistedAddress = addressService.get(1L);

        //then
        assert persistedAddress != null;
        assert persistedAddress.getId().equals(1L);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowExceptionWhenAddressIdDoesNotExist() {
        //given
        AddressPersistenceAdapter addressPersistenceAdapter = AddressPersistenceAdapterBuilder.buildAddressPersistenceAdapter();
        AddressService addressService = AddressServiceBuilder.build(null, addressPersistenceAdapter, null);

        //when
        Address persistedAddress = addressService.get(1L);
    }
}
