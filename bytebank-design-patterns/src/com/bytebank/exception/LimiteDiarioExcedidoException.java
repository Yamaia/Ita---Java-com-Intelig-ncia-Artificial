package com.bytebank.exception;

public class LimiteDiarioExcedidoException extends RegraNegocioException {
    public LimiteDiarioExcedidoException(double limite) {
        super("ERRO: LIMITE DIARIO EXCEDIDO (limite: " + limite + ")");
    }
}
