package com.bytebank.strategy;

import com.bytebank.chain.ValidadorOperacao;
import com.bytebank.chain.ValidadorValorPositivo;
import com.bytebank.exception.RegraNegocioException;

public class DepositoStrategy implements OperacaoStrategy {

    @Override
    public void executar(OperacaoContexto contexto) throws RegraNegocioException {
        ValidadorOperacao cadeia = new ValidadorValorPositivo();
        cadeia.validar(contexto);

        contexto.getContaOrigem().creditar(contexto.getValor(), getNome());
    }

    @Override
    public String getNome() {
        return "DEPOSITO";
    }
}
