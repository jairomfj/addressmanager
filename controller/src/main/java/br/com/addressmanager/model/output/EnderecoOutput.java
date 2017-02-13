package br.com.addressmanager.model.output;

import br.com.addressmanager.model.input.EnderecoInput;

import java.util.List;

public class EnderecoOutput <T> {

    private static final String DEFAULT_SUCCESS = "Sucesso";
    private static final String DEFAULT_NOT_FOUND_ENDERECO = "O endereco com o ID informado não foi encontrado";
    private static final String DEFAULT_NOT_FOUND_USER = "O ID do usuário informado não foi encontrado";
    private static final String DEFAULT_INVALID_ENDERECO = "Endereço inválido. Corrija os campos e tente novamente";

    private String mensagem;
    private T response;

    public EnderecoOutput() {}

    private EnderecoOutput(String mensagem, T response) {
        this.mensagem = mensagem;
        this.response = response;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

    public T getResponse() {
        return response;
    }

    public void setResponse(T response) {
        this.response = response;
    }

    public static EnderecoOutput buildInvalidEndereco() {
        return new EnderecoOutput<>(DEFAULT_INVALID_ENDERECO, null);
    }

    public static EnderecoOutput buildSuccess() {
        return new EnderecoOutput<>(DEFAULT_SUCCESS, null);
    }

    public static EnderecoOutput buildSuccess(EnderecoInput endereco) {
        return new EnderecoOutput<>(DEFAULT_SUCCESS, endereco);
    }

    public static EnderecoOutput buildSuccess(List enderecoIdInputList) {
        return new EnderecoOutput<>(DEFAULT_SUCCESS, enderecoIdInputList);
    }

    public static EnderecoOutput buildEnderecoNotFound() {
        return new EnderecoOutput<>(DEFAULT_NOT_FOUND_ENDERECO, null);
    }

    public static EnderecoOutput buildUserNotFound() {
        return new EnderecoOutput<>(DEFAULT_NOT_FOUND_USER, null);
    }
}
