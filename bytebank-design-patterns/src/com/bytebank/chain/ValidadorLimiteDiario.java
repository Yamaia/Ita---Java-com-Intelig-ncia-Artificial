package com.bytebank.chain;

import com.bytebank.exception.LimiteDiarioExcedidoException;
import com.bytebank.exception.RegraNegocioException;
import com.bytebank.model.Conta;
import com.bytebank.strategy.OperacaoContexto;

public class ValidadorLimiteDiario extends ValidadorOperacao {
    @Override
    protected void checar(OperacaoContexto contexto) throws RegraNegocioException {
        Conta origem = contexto.getContaOrigem();
        double totalAposOperacao = origem.getTotalMovimentadoHoje() + contexto.getValor();
        if (totalAposOperacao > origem.getLimiteDiario()) {
            throw new LimiteDiarioExcedidoException(origem.getLimiteDiario());
        }
    }
}
