package br.com.addressmanager.model;

import org.apache.commons.lang3.StringUtils;

public class Address {

    private Long id;
    private String street;
    private Long userId;
    private Long number;
    private String cep;
    private String city;
    private String state;
    private String neighborhood;
    private String complement;

    public Address() {}

    public Address(Long id, String street, Long userId, Long number, String cep, String city, String state, String neighborhood, String complement) {
        this.street = street;
        this.id = id;
        this.userId = userId;
        this.number = number;
        this.cep = cep;
        this.city = city;
        this.state = state;
        this.neighborhood = neighborhood;
        this.complement = complement;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public Long getNumber() {
        return number;
    }

    public void setNumber(Long number) {
        this.number = number;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getNeighborhood() {
        return neighborhood;
    }

    public void setNeighborhood(String neighborhood) {
        this.neighborhood = neighborhood;
    }

    public String getComplement() {
        return complement;
    }

    public void setComplement(String complement) {
        this.complement = complement;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public boolean isValid() {
        return StringUtils.isNotBlank(street)
                && StringUtils.isNotBlank(cep)
                && StringUtils.isNotBlank(city)
                && StringUtils.isNotBlank(state)
                && number != null
                && userId != null;
    }
}
