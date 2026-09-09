package com.bytebank.observer;

import com.bytebank.model.Conta;

/**
 * Observer concreto que simula o envio de uma notificação ao cliente
 * (por exemplo, um push/SMS) a cada operação bem-sucedida.
 */
public class NotificacaoClienteObserver implements TransacaoObserver {
    @Override
    public void aoRealizarOperacao(Conta conta, String tipoOperacao, double valor) {
        System.out.printf("[NOTIFICACAO] %s, sua conta %d recebeu um %s de R$ %.2f. Saldo atual: R$ %.2f%n",
                conta.getTitular(), conta.getNumero(), tipoOperacao, valor, conta.getSaldo());
    }
}
