package com.bytebank.factory;

import com.bytebank.model.Conta;
import com.bytebank.model.ContaPoupanca;

public class ContaPoupancaFactory implements ContaFactory {
    @Override
    public Conta criarConta(String titular, double saldoInicial) {
        return new ContaPoupanca(titular, saldoInicial);
    }
}
