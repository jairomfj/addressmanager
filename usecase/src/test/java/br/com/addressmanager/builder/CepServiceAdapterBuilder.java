package br.com.addressmanager.builder;

import br.com.addressmanager.CepServiceAdapter;
import br.com.addressmanager.model.CepAddress;
import br.com.addressmanager.model.CepServiceResponse;

public class CepServiceAdapterBuilder {

    public static CepServiceAdapter buildCepServiceAdapterWithSuccess(CepAddress cepAddress) {
        return cepConnectorRequest -> new CepServiceResponse(true, cepAddress);
    }

    public static CepServiceAdapter buildCepServiceAdapterWithFailure() {
        return cepConnectorRequest -> new CepServiceResponse(false, null);
    }
}
