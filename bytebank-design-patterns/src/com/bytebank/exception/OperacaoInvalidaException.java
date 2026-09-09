package com.bytebank.exception;

public class OperacaoInvalidaException extends RegraNegocioException {
    public OperacaoInvalidaException() {
        super("ERRO: OPERACAO INVALIDA");
    }
}
