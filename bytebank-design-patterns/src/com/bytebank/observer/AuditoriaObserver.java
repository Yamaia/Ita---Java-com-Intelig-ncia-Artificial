package com.bytebank.observer;

import com.bytebank.model.Conta;
import com.bytebank.singleton.AuditoriaLogger;

/**
 * Observer concreto que registra toda operação bem-sucedida no
 * log de auditoria central (Singleton).
 */
public class AuditoriaObserver implements TransacaoObserver {
    @Override
    public void aoRealizarOperacao(Conta conta, String tipoOperacao, double valor) {
        String registro = String.format("Conta %d (%s) | %s | valor=R$ %.2f | saldo_pos=R$ %.2f",
                conta.getNumero(), conta.getTipo(), tipoOperacao, valor, conta.getSaldo());
        AuditoriaLogger.getInstancia().registrar(registro);
    }
}
