package br.com.addressmanager;

import br.com.addressmanager.builder.AddressPersistenceAdapterBuilder;
import br.com.addressmanager.builder.AddressServiceBuilder;
import br.com.addressmanager.builder.CepServiceAdapterBuilder;
import br.com.addressmanager.model.Address;
import br.com.addressmanager.model.AddressComparator;
import br.com.addressmanager.model.CepAddress;
import org.junit.Test;

public class UpdateAddressServiceTest {

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowExceptionWhenAddressIsInvalid() {
        //given
        AddressService addressService = new AddressService(null, null, null);
        Address address = new Address();

        //when
        addressService.update(address);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowExceptionWhenAddressIsNull() {
        //given
        AddressService addressService = new AddressService(null, null, null);

        //when
        addressService.update(null);
    }

    @Test
    public void shouldUpdateAddressSuccessfully() {
        //given
        Address address1 = new Address(1L, "street1", 1L, 1L, "cep", "city", "state", "neighborhood", "complement");
        Address address2 = new Address(1L, "street2", 1L, 1L, "cep", "city", "state", "neighborhood", "complement");
        CepAddress cepAddress = new CepAddress("street2", "cep", "city", "state", "neighborhood");

        AddressPersistenceAdapter addressPersistenceAdapter = AddressPersistenceAdapterBuilder.buildAddressPersistenceAdapter();
        addressPersistenceAdapter.save(address1);

        CepServiceAdapter cepServiceAdapter = CepServiceAdapterBuilder.buildCepServiceAdapterWithSuccess(cepAddress);
        AddressService addressService = AddressServiceBuilder.build(cepServiceAdapter, addressPersistenceAdapter, new AddressComparator());

        //when
        addressService.update(address2);

        //then
        Address persistedAddress = addressPersistenceAdapter.findOne(address2.getId()).get();
        assert persistedAddress.getStreet().equals(address2.getStreet());
    }

    @Test(expected = InternalError.class)
    public void shouldThrowInternalErrorWhenCouldNotConnectToCepService() {
        //given
        Address address = new Address(1L, "street", 1L, 1L, "cep", "city", "state", "neighborhood", "complement");
        AddressPersistenceAdapter addressPersistenceAdapter = AddressPersistenceAdapterBuilder.buildAddressPersistenceAdapter();
        CepServiceAdapter cepServiceAdapter = CepServiceAdapterBuilder.buildCepServiceAdapterWithFailure();
        AddressService addressService = AddressServiceBuilder.build(cepServiceAdapter, addressPersistenceAdapter, new AddressComparator());

        //when
        addressService.update(address);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowExceptionWhenTheReturnedCepDoesNotMatchTheInformedOne() {
        //given
        Address address2 = new Address(1L, "street2", 1L, 1L, "cep", "city", "state", "neighborhood", "complement");
        CepAddress cepAddress = new CepAddress("street1", "cep", "city", "state", "neighborhood");

        AddressPersistenceAdapter addressPersistenceAdapter = AddressPersistenceAdapterBuilder.buildAddressPersistenceAdapter();
        CepServiceAdapter cepServiceAdapter = CepServiceAdapterBuilder.buildCepServiceAdapterWithSuccess(cepAddress);
        AddressService addressService = AddressServiceBuilder.build(cepServiceAdapter, addressPersistenceAdapter, new AddressComparator());

        //when
        addressService.create(address2);
    }
}
