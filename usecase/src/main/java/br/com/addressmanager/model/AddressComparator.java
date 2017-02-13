package br.com.addressmanager.model;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

@Component
public class AddressComparator {

    public boolean isEquals(Address address, CepAddress cepAddress) {
        if(address == null || cepAddress == null) {
            throw new IllegalArgumentException("Cannot compare null argument");
        }

        return compare(address.getCep(), cepAddress.getCep())
                && compare(address.getCity(), cepAddress.getCity())
                && compare(address.getNeighborhood(), cepAddress.getNeighborhood())
                && compare(address.getState(), cepAddress.getState())
                && compare(address.getStreet(), cepAddress.getStreet());
    }

    private boolean compare(String value1, String value2) {
        return StringUtils.equals(value1.toLowerCase(), value2.toLowerCase());
    }
}
