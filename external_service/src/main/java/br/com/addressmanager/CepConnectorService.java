package br.com.addressmanager;

import br.com.addressmanager.http.HttpConnector;
import br.com.addressmanager.http.vo.HttpRequest;
import br.com.addressmanager.http.vo.HttpResponse;
import br.com.addressmanager.model.*;
import com.google.gson.Gson;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class CepConnectorService implements CepServiceAdapter {

    private static Logger LOGGER = LoggerFactory.getLogger(CepConnectorService.class);

    @Value("${cepservice.endpoint}")
    private String endpoint;

    private final HttpConnector httpConnector;

    @Autowired
    public CepConnectorService(HttpConnector httpConnector) {
        this.httpConnector = httpConnector;
    }

    @Override
    public CepServiceResponse execute(CepServiceRequest cepConnectorRequest) {
        HttpRequest httpRequest = new HttpRequest();
        httpRequest.setUrl(endpoint);
        httpRequest.setBody(new Gson().toJson(cepConnectorRequest));

        HttpResponse httpResponse = httpConnector.post(httpRequest);

        CepServiceResponse cepServiceResponse = new CepServiceResponse();
        if(httpResponse.isSuccess()) {
            try {
                String responseBody = httpResponse.getBody();
                if(StringUtils.isNotBlank(responseBody)) {
                    CepAddressResponse cepAddressResponse = new Gson().fromJson(responseBody, CepAddressResponse.class);
                    CepAddress cepAddress = parseToCepAddress(cepAddressResponse.getEndereco());
                    cepServiceResponse.setAddress(cepAddress);
                }
                cepServiceResponse.setExecuted(true);
            } catch (Throwable e) {
                LOGGER.error("An error has occurred", e);
            }
        }

        return cepServiceResponse;
    }

    private CepAddress parseToCepAddress(Endereco endereco) {
        CepAddress cepAddress = new CepAddress();
        cepAddress.setCep(endereco.getCep());
        cepAddress.setCity(endereco.getCidade());
        cepAddress.setNeighborhood(endereco.getBairro());
        cepAddress.setState(endereco.getEstado());
        cepAddress.setStreet(endereco.getRua());
        return cepAddress;
    }
}
