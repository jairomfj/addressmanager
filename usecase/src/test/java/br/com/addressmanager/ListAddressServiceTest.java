package br.com.addressmanager;

import br.com.addressmanager.builder.AddressPersistenceAdapterBuilder;
import br.com.addressmanager.builder.AddressServiceBuilder;
import br.com.addressmanager.model.Address;
import org.junit.Test;

import java.util.List;

public class ListAddressServiceTest {

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowExceptionWhenUserIdIsNull() {
        //given
        AddressService addressService = new AddressService(null, null, null);

        //when
        addressService.list(null);
    }

    @Test
    public void shouldReturnEmptyListWhenThereIsNoAddressForTheUser() {
        //given
        AddressPersistenceAdapter addressPersistenceAdapter = AddressPersistenceAdapterBuilder.buildAddressPersistenceAdapter();
        AddressService addressService = AddressServiceBuilder.build(null, addressPersistenceAdapter, null);

        //when
        List<Address> list = addressService.list(1L);

        //then
        assert list.isEmpty();
    }

    @Test
    public void shouldReturnAddressForTheUser() {
        //given
        Address address = new Address(); address.setId(1L); address.setUserId(1L);
        AddressPersistenceAdapter addressPersistenceAdapter = AddressPersistenceAdapterBuilder.buildAddressPersistenceAdapter();
        addressPersistenceAdapter.save(address);
        AddressService addressService = AddressServiceBuilder.build(null, addressPersistenceAdapter, null);


        //when
        List<Address> list = addressService.list(1L);

        //then
        assert !list.isEmpty();
    }
}
