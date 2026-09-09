package com.bytebank.exception;

public class SaldoInsuficienteException extends RegraNegocioException {
    public SaldoInsuficienteException() {
        super("ERRO: SALDO INSUFICIENTE");
    }
}
