package com.bytebank.singleton;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Singleton: existe uma única instância deste logger em todo o sistema,
 * garantindo que todos os registros de auditoria fiquem centralizados
 * num único lugar, independente de quantas contas ou operações existam.
 */
public final class AuditoriaLogger {

    private static AuditoriaLogger instancia;

    private final List<String> registros = new ArrayList<>();

    private AuditoriaLogger() {
        // construtor privado: impede instanciação externa
    }

    public static synchronized AuditoriaLogger getInstancia() {
        if (instancia == null) {
            instancia = new AuditoriaLogger();
        }
        return instancia;
    }

    public void registrar(String mensagem) {
        registros.add(mensagem);
    }

    public List<String> getRegistros() {
        return Collections.unmodifiableList(registros);
    }

    public void imprimirRelatorio() {
        System.out.println("\n===== RELATORIO DE AUDITORIA =====");
        if (registros.isEmpty()) {
            System.out.println("Nenhuma operacao registrada.");
        } else {
            for (int i = 0; i < registros.size(); i++) {
                System.out.printf("%02d) %s%n", i + 1, registros.get(i));
            }
        }
        System.out.println("===================================");
    }
}
