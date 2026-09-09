# ByteBank — Padrões de Projeto (Java Puro)

Projeto final do módulo de **Padrões de Projeto** da trilha DIO. Simula um
cenário bancário simplificado (contas, depósito, saque e transferência)
usando **cinco Design Patterns** trabalhando juntos, sem nenhum framework.

## Padrões aplicados

| Padrão | Onde | Por quê |
|---|---|---|
| **Factory Method** | `factory/ContaFactory` + `ContaCorrenteFactory` / `ContaPoupancaFactory` | Cada tipo de conta tem regras próprias (limite diário, por exemplo); a fábrica isola a decisão de qual subclasse instanciar. |
| **Strategy** | `strategy/OperacaoStrategy` + `DepositoStrategy` / `SaqueStrategy` / `TransferenciaStrategy` | Cada operação bancária sabe validar e executar a si mesma; trocar/adicionar um tipo de operação não exige `if/else` em nenhum outro lugar do código. |
| **Chain of Responsibility** | `chain/ValidadorOperacao` + validadores concretos | As regras de negócio (valor positivo, saldo suficiente, limite diário) são elos independentes de uma cadeia; cada Strategy monta só os elos que precisa. |
| **Observer** | `observer/TransacaoObserver` + `NotificacaoClienteObserver` / `AuditoriaObserver`, com `Conta` como *subject* | A conta não sabe (nem precisa saber) quem está interessado nela — só notifica; novos interessados (ex.: um serviço de e-mail) entram sem tocar em `Conta`. |
| **Singleton** | `singleton/AuditoriaLogger` | Existe um único log de auditoria compartilhado por todas as contas do sistema, com ponto de acesso global controlado. |

## Estrutura

```
src/com/bytebank/
├── app/         → Main.java (ponto de entrada, amarra os padrões)
├── model/       → Conta (abstrata), ContaCorrente, ContaPoupanca
├── factory/     → Factory Method
├── strategy/    → Strategy + contexto da operação + resolvedor de tipo
├── chain/       → Chain of Responsibility (validações)
├── observer/    → Observer
├── singleton/   → Singleton (log de auditoria)
└── exception/   → Exceções de negócio (equivalentes às mensagens de erro)
```

## Como rodar

```bash
find src -name "*.java" > sources.txt
javac -d out @sources.txt
java -cp out com.bytebank.app.Main
```

## Fluxo de uma operação (exemplo: saque)

1. `Main` pede ao `OperacaoStrategyResolver` a strategy correspondente ao texto digitado (`"SACAR"`).
2. `SaqueStrategy` monta sua cadeia: `ValidadorValorPositivo → ValidadorSaldoSuficiente → ValidadorLimiteDiario`.
3. Se qualquer validador falhar, uma `RegraNegocioException` específica é lançada e a operação é abortada sem alterar o saldo.
4. Se passar por toda a cadeia, `Conta.debitar(...)` é chamado, o saldo é atualizado e **todos os observers registrados** são notificados (cliente + auditoria).

## Possíveis evoluções

- **Decorator** para aplicar taxas/IOF sobre certas operações sem alterar as Strategies existentes.
- **Builder** para montar um extrato/relatório de conta mais elaborado.
- Persistência real (arquivo ou banco) no lugar da lista em memória do `AuditoriaLogger`.
