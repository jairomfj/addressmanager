package br.com.addressmanager.model;

public class CepServiceResponse {

    private boolean executed = false;
    private CepAddress address;

    public CepServiceResponse() {
    }

    public CepServiceResponse(boolean executed, CepAddress address) {
        this.executed = executed;
        this.address = address;
    }

    public boolean executedSuccessfully() {
        return executed;
    }

    public void setExecuted(boolean executed) {
        this.executed = executed;
    }

    public CepAddress getAddress() {
        return address;
    }

    public void setAddress(CepAddress address) {
        this.address = address;
    }
}
