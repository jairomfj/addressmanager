package br.com.addressmanager.model;

public class EnderecoOutput {

    private static final String DEFAULT_SUCCESS = "Sucesso";
    private static final String DEFAULT_INVALID_ENDERECO = "Endereço inválido. Corrija os campos e tente novamente";

    private String mensagem;
    private EnderecoInput endereco;

    public EnderecoOutput() {}

    public EnderecoOutput(String mensagem, EnderecoInput endereco) {
        this.mensagem = mensagem;
        this.endereco = endereco;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

    public EnderecoInput getEndereco() {
        return endereco;
    }

    public void setEndereco(EnderecoInput endereco) {
        this.endereco = endereco;
    }

    public static EnderecoOutput buildSuccess(EnderecoInput endereco) {
        return new EnderecoOutput(DEFAULT_SUCCESS, endereco);
    }

    public static EnderecoOutput buildInvalidEndereco() {
        return new EnderecoOutput(DEFAULT_INVALID_ENDERECO, null);
    }
}
