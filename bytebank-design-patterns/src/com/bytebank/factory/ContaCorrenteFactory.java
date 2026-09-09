package com.bytebank.factory;

import com.bytebank.model.Conta;
import com.bytebank.model.ContaCorrente;

public class ContaCorrenteFactory implements ContaFactory {
    @Override
    public Conta criarConta(String titular, double saldoInicial) {
        return new ContaCorrente(titular, saldoInicial);
    }
}
