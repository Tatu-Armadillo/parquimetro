package br.com.fiap.parquimetro.exception;

public class BusinessException extends RuntimeException {

    public BusinessException(final String mensagem) {
        super(mensagem);
    }
}