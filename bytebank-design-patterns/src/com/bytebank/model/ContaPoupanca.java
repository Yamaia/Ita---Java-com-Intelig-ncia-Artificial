package com.bytebank.model;

public class ContaPoupanca extends Conta {

    private static final double LIMITE_DIARIO = 2000.0;

    public ContaPoupanca(String titular, double saldoInicial) {
        super(titular, saldoInicial);
    }

    @Override
    public double getLimiteDiario() {
        return LIMITE_DIARIO;
    }

    @Override
    public String getTipo() {
        return "POUPANCA";
    }
}
