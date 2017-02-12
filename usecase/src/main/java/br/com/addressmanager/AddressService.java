package br.com.addressmanager;

import br.com.addressmanager.model.Address;
import br.com.addressmanager.model.CepServiceRequest;
import br.com.addressmanager.model.CepServiceResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AddressService {

    private final CepServiceAdapter cepServiceAdapter;
    private final AddressPersistenceAdapter addressPersistenceAdapter;

    @Autowired
    public AddressService(CepServiceAdapter cepServiceAdapter,
                          AddressPersistenceAdapter addressPersistenceAdapter) {
        this.cepServiceAdapter = cepServiceAdapter;
        this.addressPersistenceAdapter = addressPersistenceAdapter;
    }

    public Address create(Address address) {
        if(address == null || !address.isValid()) {
            throw new IllegalArgumentException("Address does not have a valid format");
        }

        CepServiceResponse cepServiceResponse = cepServiceAdapter.execute(new CepServiceRequest(address.getCep()));

        if (!cepServiceResponse.executedSuccessfully()) {
            throw new InternalError("Could not validate cep");
        }

//        TODO validar cepServiceResponse com dados digitados

        addressPersistenceAdapter.save(address);
        return address;
    }

}
