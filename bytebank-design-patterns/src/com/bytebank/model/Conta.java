package com.bytebank.model;

import com.bytebank.observer.TransacaoObserver;

import java.util.ArrayList;
import java.util.List;

/**
 * Classe base para os tipos de conta do ByteBank.
 * Atua como "Subject" no padrão Observer: mantém uma lista de observadores
 * e os notifica sempre que uma operação é concluída com sucesso.
 */
public abstract class Conta {

    private static int proximoNumero = 1000;

    private final int numero;
    private final String titular;
    private double saldo;
    private double totalMovimentadoHoje;

    private final List<TransacaoObserver> observadores = new ArrayList<>();

    protected Conta(String titular, double saldoInicial) {
        this.numero = proximoNumero++;
        this.titular = titular;
        this.saldo = saldoInicial;
    }

    public void adicionarObservador(TransacaoObserver observador) {
        observadores.add(observador);
    }

    private void notificarObservadores(String tipoOperacao, double valor) {
        for (TransacaoObserver observador : observadores) {
            observador.aoRealizarOperacao(this, tipoOperacao, valor);
        }
    }

    /** Chamado pelas Strategies após a validação passar, para de fato mover o saldo. */
    public void creditar(double valor, String tipoOperacao) {
        this.saldo += valor;
        this.totalMovimentadoHoje += valor;
        notificarObservadores(tipoOperacao, valor);
    }

    public void debitar(double valor, String tipoOperacao) {
        this.saldo -= valor;
        this.totalMovimentadoHoje += valor;
        notificarObservadores(tipoOperacao, valor);
    }

    /** Usado pela cadeia de validação para checar limite diário. */
    public abstract double getLimiteDiario();

    public int getNumero() {
        return numero;
    }

    public String getTitular() {
        return titular;
    }

    public double getSaldo() {
        return saldo;
    }

    public double getTotalMovimentadoHoje() {
        return totalMovimentadoHoje;
    }

    public abstract String getTipo();

    @Override
    public String toString() {
        return String.format("Conta{numero=%d, tipo=%s, titular='%s', saldo=%.2f}",
                numero, getTipo(), titular, saldo);
    }
}
