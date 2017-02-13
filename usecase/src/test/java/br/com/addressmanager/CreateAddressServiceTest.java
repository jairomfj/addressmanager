package br.com.addressmanager;

import br.com.addressmanager.builder.AddressPersistenceAdapterBuilder;
import br.com.addressmanager.builder.AddressServiceBuilder;
import br.com.addressmanager.builder.CepServiceAdapterBuilder;
import br.com.addressmanager.model.Address;
import br.com.addressmanager.model.AddressComparator;
import br.com.addressmanager.model.CepAddress;
import org.junit.Test;

public class CreateAddressServiceTest {

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowExceptionWhenAddressIsInvalid() {
        //given
        AddressService addressService = new AddressService(null, null, null);
        Address address = new Address();

        //when
        addressService.create(address);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowExceptionWhenAddressIsNull() {
        //given
        AddressService addressService = new AddressService(null, null, null);

        //when
        addressService.create(null);
    }

    @Test
    public void shouldCreateAddressSuccessfully() {
        //given
        CepAddress cepAddress = new CepAddress("street", "cep", "city", "state", "neighborhood");
        Address address = new Address(1L, "street", 1L, 1L, "cep", "city", "state", "neighborhood", "complement");
        AddressPersistenceAdapter addressPersistenceAdapter = AddressPersistenceAdapterBuilder.buildAddressPersistenceAdapter();
        CepServiceAdapter cepServiceAdapter = CepServiceAdapterBuilder.buildCepServiceAdapterWithSuccess(cepAddress);
        AddressService addressService = AddressServiceBuilder.build(cepServiceAdapter, addressPersistenceAdapter, new AddressComparator());

        //when
        Address createdAddress = addressService.create(address);

        //then
        assert addressPersistenceAdapter.findAllByUserId(1L).size() > 0;
        assert 1L == createdAddress.getId();
    }

    @Test(expected = InternalError.class)
    public void shouldThrowInternalErrorWhenCouldNotConnectToCepService() {
        //given
        Address address = new Address(1L, "street", 1L, 1L, "cep", "city", "state", "neighborhood", "complement");
        AddressPersistenceAdapter addressPersistenceAdapter = AddressPersistenceAdapterBuilder.buildAddressPersistenceAdapter();
        CepServiceAdapter cepServiceAdapter = CepServiceAdapterBuilder.buildCepServiceAdapterWithFailure();
        AddressService addressService = AddressServiceBuilder.build(cepServiceAdapter, addressPersistenceAdapter, new AddressComparator());

        //when
        addressService.create(address);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowExceptionWhenTheReturnedCepDoesNotMatchTheInformedOne() {
        //given
        CepAddress cepAddress = new CepAddress("street1", "cep", "city", "state", "neighborhood");
        Address address = new Address("street", 1L, 1L, "cep", "city", "state", "neighborhood", "complement");
        AddressPersistenceAdapter addressPersistenceAdapter = AddressPersistenceAdapterBuilder.buildAddressPersistenceAdapter();
        CepServiceAdapter cepServiceAdapter = CepServiceAdapterBuilder.buildCepServiceAdapterWithSuccess(cepAddress);
        AddressService addressService = AddressServiceBuilder.build(cepServiceAdapter, addressPersistenceAdapter, new AddressComparator());

        //when
        addressService.create(address);
    }

}
