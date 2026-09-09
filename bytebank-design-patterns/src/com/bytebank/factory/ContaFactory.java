package com.bytebank.factory;

import com.bytebank.model.Conta;

/**
 * Factory Method: define o contrato para criação de contas, deixando que
 * cada subclasse decida QUAL tipo concreto de Conta instanciar.
 */
public interface ContaFactory {
    Conta criarConta(String titular, double saldoInicial);
}
