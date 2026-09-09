package com.bytebank.chain;

import com.bytebank.exception.RegraNegocioException;
import com.bytebank.exception.ValorInvalidoException;
import com.bytebank.strategy.OperacaoContexto;

public class ValidadorValorPositivo extends ValidadorOperacao {
    @Override
    protected void checar(OperacaoContexto contexto) throws RegraNegocioException {
        if (contexto.getValor() <= 0) {
            throw new ValorInvalidoException();
        }
    }
}
