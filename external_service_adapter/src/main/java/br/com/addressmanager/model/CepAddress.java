package br.com.addressmanager.model;

public class CepAddress {
    private String street;
    private String cep;
    private String city;
    private String state;
    private String neighborhood;

    public CepAddress() {
    }

    public CepAddress(String street, String cep, String city, String state, String neighborhood) {
        this.street = street;
        this.cep = cep;
        this.city = city;
        this.state = state;
        this.neighborhood = neighborhood;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
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
}
