package com.bytebank.exception;

/**
 * Exceção base para todas as violações de regras de negócio do ByteBank.
 * Cada handler da cadeia de validação (Chain of Responsibility) lança
 * uma subclasse específica desta exceção.
 */
public class RegraNegocioException extends Exception {
    public RegraNegocioException(String mensagem) {
        super(mensagem);
    }
}
