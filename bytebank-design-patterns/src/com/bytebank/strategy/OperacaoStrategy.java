package com.bytebank.strategy;

import com.bytebank.exception.RegraNegocioException;

/**
 * Strategy: cada tipo de operação bancária (Depósito, Saque, Transferência)
 * implementa esta interface, encapsulando sua própria regra de validação
 * (via Chain of Responsibility) e sua própria forma de mover o saldo.
 */
public interface OperacaoStrategy {
    void executar(OperacaoContexto contexto) throws RegraNegocioException;

    /** Nome usado nas mensagens de notificação/auditoria. */
    String getNome();
}
