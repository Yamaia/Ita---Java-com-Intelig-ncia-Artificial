package com.bytebank.strategy;

import com.bytebank.model.Conta;

/**
 * Objeto de contexto que transporta os dados de uma operação bancária
 * entre a Strategy e a cadeia de validadores (Chain of Responsibility).
 * contaDestino é usada apenas em transferências.
 */
public class OperacaoContexto {

    private final Conta contaOrigem;
    private final Conta contaDestino;
    private final double valor;

    public OperacaoContexto(Conta contaOrigem, double valor) {
        this(contaOrigem, null, valor);
    }

    public OperacaoContexto(Conta contaOrigem, Conta contaDestino, double valor) {
        this.contaOrigem = contaOrigem;
        this.contaDestino = contaDestino;
        this.valor = valor;
    }

    public Conta getContaOrigem() {
        return contaOrigem;
    }

    public Conta getContaDestino() {
        return contaDestino;
    }

    public double getValor() {
        return valor;
    }
}
