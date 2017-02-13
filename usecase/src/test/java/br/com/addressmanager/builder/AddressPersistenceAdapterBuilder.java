package br.com.addressmanager.builder;

import br.com.addressmanager.AddressPersistenceAdapter;
import br.com.addressmanager.model.Address;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class AddressPersistenceAdapterBuilder {

    public static AddressPersistenceAdapter buildAddressPersistenceAdapter() {
        return new AddressPersistenceAdapter() {
            private List<Address> addressList = new ArrayList<>();
            private Long id = 1L;

            @Override
            public Optional<Address> findOne(Long id) {
                return addressList
                        .stream()
                        .filter((address -> address.getId().equals(id)))
                        .findFirst();
            }

            @Override
            public void save(Address address) {
                address.setId(id);
                addressList.add(address);
                id++;
            }

            @Override
            public void update(Address addressToBeUpdated) {
                int index = -1;
                for(int i = 0; i < addressList.size(); i++) {
                    if(addressList.get(i).getId().equals(addressToBeUpdated.getId())) {
                        index = i;
                    }
                }

                if(index != -1) addressList.remove(index);
                addressList.add(addressToBeUpdated);
            }

            @Override
            public void delete(Long id) {
                int index = -1;
                for(int i = 0; i < addressList.size(); i++) {
                    if(addressList.get(i).getId().equals(id)) {
                        index = i;
                    }
                }

                if(index != -1) addressList.remove(index);
            }

            @Override
            public List<Address> findAllByUserId(Long userId) {
                return addressList
                        .stream()
                        .filter((address -> address.getUserId().equals(userId)))
                        .collect(Collectors.toList());
            }
        };
    }
}
