package com.bytebank.strategy;

import com.bytebank.exception.OperacaoInvalidaException;
import com.bytebank.exception.RegraNegocioException;

import java.util.HashMap;
import java.util.Map;

/**
 * Resolve qual OperacaoStrategy usar a partir do texto informado pelo
 * usuário (ex.: "DEPOSITAR", "SACAR"), evitando if/else espalhado pelo
 * código cliente. Se o tipo não for reconhecido, lança OperacaoInvalidaException.
 */
public class OperacaoStrategyResolver {

    private final Map<String, OperacaoStrategy> estrategias = new HashMap<>();

    public OperacaoStrategyResolver() {
        registrar("DEPOSITAR", new DepositoStrategy());
        registrar("SACAR", new SaqueStrategy());
        registrar("TRANSFERIR", new TransferenciaStrategy());
    }

    private void registrar(String tipo, OperacaoStrategy strategy) {
        estrategias.put(tipo, strategy);
    }

    public OperacaoStrategy resolver(String tipoOperacao) throws RegraNegocioException {
        OperacaoStrategy strategy = estrategias.get(tipoOperacao);
        if (strategy == null) {
            throw new OperacaoInvalidaException();
        }
        return strategy;
    }
}
