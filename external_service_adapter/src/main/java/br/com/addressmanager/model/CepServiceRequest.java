package br.com.addressmanager.model;

public class CepServiceRequest {

    private String cep;

    public CepServiceRequest(String cep) {
        this.cep = cep;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }
}
