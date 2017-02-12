package br.com.addressmanager.http;

import br.com.addressmanager.http.vo.HttpRequest;
import br.com.addressmanager.http.vo.HttpResponse;
import com.squareup.okhttp.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class HttpConnector {

    private static Logger LOGGER = LoggerFactory.getLogger(HttpConnector.class);

    public static final MediaType JSON_MEDIA_TYPE = MediaType.parse("application/json; charset=utf-8");

    public HttpResponse post(HttpRequest httpRequest) {
        Request request = new Request.Builder()
                .url(httpRequest.getUrl())
                .post(RequestBody.create(JSON_MEDIA_TYPE, httpRequest.getBody()))
                .build();

        return execute(request);
    }


    private HttpResponse execute(Request request) {

        HttpResponse httpResponse = new HttpResponse();
        try {
            OkHttpClient client = new OkHttpClient();
            Response response = client.newCall(request).execute();

            httpResponse.setStatus(response.code());
            httpResponse.setBody(response.body().string());
        } catch (IOException e) {
            LOGGER.error("An error has occorred", e);
            httpResponse.setStatus(500);
        }

        return httpResponse;
    }
}
