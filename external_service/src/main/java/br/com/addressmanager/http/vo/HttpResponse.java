package br.com.addressmanager.http.vo;

public class HttpResponse {

    private int status;
    private String body;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public boolean isSuccess() {
        return status == 200;
    }

    public boolean hasError() {
        return !isSuccess();
    }
}
