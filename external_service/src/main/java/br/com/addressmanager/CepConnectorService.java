package br.com.addressmanager;

import br.com.addressmanager.http.HttpConnector;
import br.com.addressmanager.http.vo.HttpRequest;
import br.com.addressmanager.http.vo.HttpResponse;
import br.com.addressmanager.model.Address;
import br.com.addressmanager.model.CepConnectorRequest;
import br.com.addressmanager.model.CepServiceResponse;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class CepConnectorService implements CepServiceAdapter {

    @Value("cepservice.endpoint")
    private String endpoint;

    private final HttpConnector httpConnector;

    @Autowired
    public CepConnectorService(HttpConnector httpConnector) {
        this.httpConnector = httpConnector;
    }

    @Override
    public CepServiceResponse execute(CepConnectorRequest cepConnectorRequest) {
        HttpRequest httpRequest = new HttpRequest();
        httpRequest.setUrl(endpoint);
        httpRequest.setBody(new Gson().toJson(cepConnectorRequest));

        HttpResponse httpResponse = httpConnector.post(httpRequest);

        CepServiceResponse cepServiceResponse = new CepServiceResponse();
        if(httpResponse.isSuccess()) {
            cepServiceResponse.setAddress(new Gson().fromJson(httpResponse.getBody(), Address.class));
        }

        return cepServiceResponse;
    }
}
