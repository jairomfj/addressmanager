package br.com.addressmanager;

import br.com.addressmanager.model.CepConnectorRequest;
import br.com.addressmanager.model.CepServiceResponse;

public interface CepServiceAdapter {
    CepServiceResponse execute(CepConnectorRequest cepConnectorRequest);
}
