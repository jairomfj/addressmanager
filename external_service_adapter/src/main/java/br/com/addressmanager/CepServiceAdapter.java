package br.com.addressmanager;

import br.com.addressmanager.model.CepServiceRequest;
import br.com.addressmanager.model.CepServiceResponse;

public interface CepServiceAdapter {
    CepServiceResponse execute(CepServiceRequest cepConnectorRequest);
}
