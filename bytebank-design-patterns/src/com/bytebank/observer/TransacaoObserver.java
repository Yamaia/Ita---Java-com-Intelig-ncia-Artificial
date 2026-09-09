package com.bytebank.observer;

import com.bytebank.model.Conta;

/**
 * Observer: qualquer interessado em ser avisado sobre operações
 * concluídas com sucesso em uma Conta implementa esta interface.
 */
public interface TransacaoObserver {
    void aoRealizarOperacao(Conta conta, String tipoOperacao, double valor);
}
