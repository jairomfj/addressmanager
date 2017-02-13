package br.com.addressmanager;

import br.com.addressmanager.builder.AddressBuilder;
import br.com.addressmanager.builder.AddressJPABuilder;
import br.com.addressmanager.jpa.AddressRepositoryJPA;
import br.com.addressmanager.model.Address;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AddressRepository implements AddressPersistenceAdapter {

    private final AddressRepositoryJPA addressRepositoryJPA;

    @Autowired
    public AddressRepository(AddressRepositoryJPA addressRepositoryJPA) {
        this.addressRepositoryJPA = addressRepositoryJPA;
    }

    @Override
    public Optional<Address> findOne(Long id) {
        Optional<Address> address = Optional.empty();
        AddressJPA addressJPA = this.addressRepositoryJPA.findOne(id);
        if(addressJPA != null) {
            address = Optional.of(AddressBuilder.build(addressJPA));
        }

        return address;
    }

    @Override
    public void save(Address address) {
        AddressJPA addressJPA = AddressJPABuilder.build(address);
        this.addressRepositoryJPA.save(addressJPA);
        address.setId(addressJPA.getId());
    }

    @Override
    public void update(Address address) {
        if(address == null || address.getId() == null) {
            throw new IllegalArgumentException("Address cannot be null");
        }

        AddressJPA addressJPA = this.addressRepositoryJPA.findOne(address.getId());
        if(addressJPA == null) {
            throw new IllegalArgumentException("Could not find persisted address for id: " + address.getId());
        }

        AddressBuilder.mergeAddressToAddressJPA(address, addressJPA);
        this.addressRepositoryJPA.save(addressJPA);
    }

    @Override
    public void delete(Long addressId) {
        AddressJPA addressJPA = this.addressRepositoryJPA.findOne(addressId);
        if(addressJPA != null) {
            this.addressRepositoryJPA.delete(addressJPA);
        }
    }

    @Override
    public List<Address> findAllByUserId(Long userId) {
        List<AddressJPA> addressJPAList = this.addressRepositoryJPA.findAllByUserId(userId);
        return addressJPAList
                .stream()
                .map(AddressBuilder::build)
                .collect(Collectors.toList());
    }
}
