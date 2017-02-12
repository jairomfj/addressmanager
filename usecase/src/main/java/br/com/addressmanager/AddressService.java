package br.com.addressmanager;

import br.com.addressmanager.model.Address;
import br.com.addressmanager.model.AddressCompator;
import br.com.addressmanager.model.CepServiceRequest;
import br.com.addressmanager.model.CepServiceResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AddressService {

    private static Logger LOGGER = LoggerFactory.getLogger(AddressService.class);

    private final CepServiceAdapter cepServiceAdapter;
    private final AddressPersistenceAdapter addressPersistenceAdapter;
    private final AddressCompator addressCompator;

    @Autowired
    public AddressService(CepServiceAdapter cepServiceAdapter,
                          AddressPersistenceAdapter addressPersistenceAdapter,
                          AddressCompator addressCompator) {
        this.cepServiceAdapter = cepServiceAdapter;
        this.addressPersistenceAdapter = addressPersistenceAdapter;
        this.addressCompator = addressCompator;
    }

    public Address create(Address address) {
        if(address == null || !address.isValid()) {
            throw new IllegalArgumentException("Address does not have a valid format");
        }

        LOGGER.info("Creating new address for user: " + address.getUserId());
        CepServiceResponse cepServiceResponse = cepServiceAdapter.execute(new CepServiceRequest(address.getCep()));

        if (!cepServiceResponse.executedSuccessfully()) {
            throw new InternalError("Could not validate cep");
        }

        if(!addressCompator.isEquals(address, cepServiceResponse.getAddress())) {
            throw new IllegalArgumentException("Address does not correspond to the CEP's address");
        }

        addressPersistenceAdapter.save(address);
        return address;
    }

}
