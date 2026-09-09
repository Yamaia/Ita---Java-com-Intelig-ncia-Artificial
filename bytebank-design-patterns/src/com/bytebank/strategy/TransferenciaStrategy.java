package com.bytebank.strategy;

import com.bytebank.chain.ValidadorLimiteDiario;
import com.bytebank.chain.ValidadorOperacao;
import com.bytebank.chain.ValidadorSaldoSuficiente;
import com.bytebank.chain.ValidadorValorPositivo;
import com.bytebank.exception.RegraNegocioException;

public class TransferenciaStrategy implements OperacaoStrategy {

    @Override
    public void executar(OperacaoContexto contexto) throws RegraNegocioException {
        ValidadorOperacao cadeia = new ValidadorValorPositivo();
        cadeia.encadear(new ValidadorSaldoSuficiente(true))
              .encadear(new ValidadorLimiteDiario());
        cadeia.validar(contexto);

        contexto.getContaOrigem().debitar(contexto.getValor(), getNome() + "_ENVIADA");
        contexto.getContaDestino().creditar(contexto.getValor(), getNome() + "_RECEBIDA");
    }

    @Override
    public String getNome() {
        return "TRANSFERENCIA";
    }
}
