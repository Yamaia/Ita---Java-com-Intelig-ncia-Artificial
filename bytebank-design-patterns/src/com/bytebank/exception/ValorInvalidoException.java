package com.bytebank.exception;

public class ValorInvalidoException extends RegraNegocioException {
    public ValorInvalidoException() {
        super("ERRO: VALOR INVALIDO");
    }
}
