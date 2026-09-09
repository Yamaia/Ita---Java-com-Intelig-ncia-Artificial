package com.bytebank.model;

public class ContaCorrente extends Conta {

    private static final double LIMITE_DIARIO = 5000.0;

    public ContaCorrente(String titular, double saldoInicial) {
        super(titular, saldoInicial);
    }

    @Override
    public double getLimiteDiario() {
        return LIMITE_DIARIO;
    }

    @Override
    public String getTipo() {
        return "CORRENTE";
    }
}
