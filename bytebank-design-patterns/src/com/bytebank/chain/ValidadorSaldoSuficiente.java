package com.bytebank.chain;

import com.bytebank.exception.RegraNegocioException;
import com.bytebank.exception.SaldoInsuficienteException;
import com.bytebank.strategy.OperacaoContexto;

/**
 * Só se aplica a operações que debitam da conta de origem (saque e
 * transferência); depósitos passam direto por este elo.
 */
public class ValidadorSaldoSuficiente extends ValidadorOperacao {

    private final boolean debitaContaOrigem;

    public ValidadorSaldoSuficiente(boolean debitaContaOrigem) {
        this.debitaContaOrigem = debitaContaOrigem;
    }

    @Override
    protected void checar(OperacaoContexto contexto) throws RegraNegocioException {
        if (debitaContaOrigem && contexto.getValor() > contexto.getContaOrigem().getSaldo()) {
            throw new SaldoInsuficienteException();
        }
    }
}
