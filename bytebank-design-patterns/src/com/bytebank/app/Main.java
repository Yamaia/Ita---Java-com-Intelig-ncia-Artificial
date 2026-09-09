package com.bytebank.app;

import com.bytebank.exception.RegraNegocioException;
import com.bytebank.factory.ContaCorrenteFactory;
import com.bytebank.factory.ContaFactory;
import com.bytebank.factory.ContaPoupancaFactory;
import com.bytebank.model.Conta;
import com.bytebank.observer.AuditoriaObserver;
import com.bytebank.observer.NotificacaoClienteObserver;
import com.bytebank.singleton.AuditoriaLogger;
import com.bytebank.strategy.OperacaoContexto;
import com.bytebank.strategy.OperacaoStrategy;
import com.bytebank.strategy.OperacaoStrategyResolver;

/**
 * Ponto de entrada que demonstra os cinco padrões de projeto trabalhando
 * juntos em um cenário bancário simplificado:
 *
 *   - Factory Method   -> criação das contas (Corrente / Poupança)
 *   - Strategy         -> cada tipo de operação sabe como validar e se executar
 *   - Chain of Responsibility -> pipeline de validações dentro de cada Strategy
 *   - Observer         -> Conta notifica cliente e auditoria a cada operação
 *   - Singleton        -> log de auditoria único, compartilhado por todas as contas
 */
public class Main {

    public static void main(String[] args) {
        ContaFactory fabricaCorrente = new ContaCorrenteFactory();
        ContaFactory fabricaPoupanca = new ContaPoupancaFactory();

        Conta contaJoao = fabricaCorrente.criarConta("Joao", 100);
        Conta contaMaria = fabricaPoupanca.criarConta("Maria", 50);

        // Observer: cada conta pode ter observadores diferentes
        contaJoao.adicionarObservador(new NotificacaoClienteObserver());
        contaJoao.adicionarObservador(new AuditoriaObserver());

        contaMaria.adicionarObservador(new AuditoriaObserver());

        System.out.println("Contas criadas:");
        System.out.println(" - " + contaJoao);
        System.out.println(" - " + contaMaria);
        System.out.println();

        OperacaoStrategyResolver resolver = new OperacaoStrategyResolver();

        executarOperacao(resolver, "DEPOSITAR", contaJoao, null, 50);
        executarOperacao(resolver, "SACAR", contaJoao, null, 30);
        executarOperacao(resolver, "SACAR", contaMaria, null, 999);
        executarOperacao(resolver, "TRANSFERIR", contaJoao, contaMaria, 40);
        executarOperacao(resolver, "PIX", contaJoao, null, 10);
        executarOperacao(resolver, "DEPOSITAR", contaJoao, null, -5);

        System.out.println("\nSaldos finais:");
        System.out.println(" - " + contaJoao);
        System.out.println(" - " + contaMaria);

        AuditoriaLogger.getInstancia().imprimirRelatorio();
    }

    private static void executarOperacao(OperacaoStrategyResolver resolver, String tipo,
                                          Conta origem, Conta destino, double valor) {
        try {
            OperacaoStrategy strategy = resolver.resolver(tipo);
            OperacaoContexto contexto = (destino == null)
                    ? new OperacaoContexto(origem, valor)
                    : new OperacaoContexto(origem, destino, valor);

            strategy.executar(contexto);
            System.out.printf("OK %s de R$ %.2f na conta %d%n", tipo, valor, origem.getNumero());

        } catch (RegraNegocioException e) {
            System.out.println(e.getMessage());
        }
    }
}
