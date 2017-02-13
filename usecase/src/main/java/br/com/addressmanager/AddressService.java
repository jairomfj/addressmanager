package br.com.addressmanager;

import br.com.addressmanager.model.Address;
import br.com.addressmanager.model.AddressComparator;
import br.com.addressmanager.model.CepServiceRequest;
import br.com.addressmanager.model.CepServiceResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AddressService {

    private static Logger LOGGER = LoggerFactory.getLogger(AddressService.class);

    private final CepServiceAdapter cepServiceAdapter;
    private final AddressPersistenceAdapter addressPersistenceAdapter;
    private final AddressComparator addressCompator;

    @Autowired
    public AddressService(CepServiceAdapter cepServiceAdapter,
                          AddressPersistenceAdapter addressPersistenceAdapter,
                          AddressComparator addressCompator) {
        this.cepServiceAdapter = cepServiceAdapter;
        this.addressPersistenceAdapter = addressPersistenceAdapter;
        this.addressCompator = addressCompator;
    }

    public Address create(Address address) {
        if(address == null || !address.isValid()) {
            throw new IllegalArgumentException("Address does not have a valid format");
        }

        LOGGER.info("Creating new address for user: " + address.getUserId());
        validateCEPAddress(address);
        addressPersistenceAdapter.save(address);
        return address;
    }

    public void delete(Long id) {
        if(id == null) {
            throw new IllegalArgumentException("Address does not have a valid format");
        }

        LOGGER.info("Deleting address id: " + id);
        addressPersistenceAdapter.delete(id);
    }

    public void update(Address address) {
        if(address == null || !address.isValid()) {
            throw new IllegalArgumentException("Address does not have a valid format");
        }

        LOGGER.info("Updating address id: " + address.getId());
        validateCEPAddress(address);
        addressPersistenceAdapter.update(address);
    }

    public Address get(Long id) {
        if(id == null) {
            throw new IllegalArgumentException("Id cannot be null");
        }

        LOGGER.info("Getting address id: " + id);
        Optional<Address> addressOptional = addressPersistenceAdapter.findOne(id);
        if(addressOptional.isPresent()) {
            return addressOptional.get();
        }

        throw new IllegalArgumentException("Address does not exist");
    }

    public List<Address> list(Long userId) {
        if(userId == null) {
            throw new IllegalArgumentException("UserId cannot be null");
        }

        LOGGER.info("Getting addresses from userId: " + userId);
        return addressPersistenceAdapter.findAllByUserId(userId);
    }

    private void validateCEPAddress(Address address) {
        LOGGER.info("Validating CEP address: " + address.getCep());

        CepServiceResponse cepServiceResponse = cepServiceAdapter.execute(new CepServiceRequest(address.getCep()));
        if (!cepServiceResponse.executedSuccessfully()) {
            throw new InternalError("Could not validate cep");
        }

        if(!addressCompator.isEquals(address, cepServiceResponse.getAddress())) {
            throw new IllegalArgumentException("Address does not correspond to the CEP's address");
        }

    }
}
