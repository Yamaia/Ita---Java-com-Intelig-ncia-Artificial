package com.bytebank.chain;

import com.bytebank.exception.RegraNegocioException;
import com.bytebank.strategy.OperacaoContexto;

/**
 * Chain of Responsibility: cada validador decide se trata a validação
 * e repassa para o próximo elo da cadeia. Se algum elo encontrar uma
 * violação, lança a exceção correspondente e interrompe a cadeia.
 */
public abstract class ValidadorOperacao {

    private ValidadorOperacao proximo;

    public ValidadorOperacao encadear(ValidadorOperacao proximoValidador) {
        this.proximo = proximoValidador;
        return proximoValidador;
    }

    public final void validar(OperacaoContexto contexto) throws RegraNegocioException {
        checar(contexto);
        if (proximo != null) {
            proximo.validar(contexto);
        }
    }

    /** Cada validador concreto implementa apenas a sua própria checagem. */
    protected abstract void checar(OperacaoContexto contexto) throws RegraNegocioException;
}
