# dio-java-challenges

Desafios práticos da trilha Java/Spring Boot da DIO e Itaú, reunidos num único repositório de portfólio. Cada projeto explora um pilar diferente de arquitetura backend em Java.

## Projetos

### 🏦 bytebank-design-patterns

Sistema bancário construído em Java puro (sem frameworks) como projeto final do módulo de Padrões de Projeto. Aplica padrões clássicos do GoF sobre um domínio financeiro:

- Factory Method — criação de tipos de conta/transação
- Strategy — regras de negócio intercambiáveis
- Chain of Responsibility — validações em cadeia
- Observer — notificação de eventos
- Singleton — controle de instância única

Foco do desafio: aplicar os padrões de forma justificada, não decorativa, sobre um domínio bancário do zero.

### 🎙️ budget-voice-ai

API de orçamento pessoal construída com Spring Boot + Spring AI, evoluída a partir do desafio final da trilha Spring Boot. Transforma comandos de voz em transações financeiras: áudio é transcrito com Whisper, um ChatClient com Tool Calling interpreta a intenção e escolhe o caso de uso certo, que persiste ou consulta transações, e a resposta final é convertida de volta em áudio.

Evolução implementada: novas ferramentas de consulta (total gasto por categoria, listagem geral), validação de domínio antes de persistir transações, e testes unitários cobrindo as regras de negócio.

## Por que estes dois juntos

Os dois projetos partem do mesmo domínio (transações financeiras) mas resolvem problemas de arquitetura opostos: um foca em desenhar um sistema do zero com padrões de projeto clássicos, o outro em integrar IA a uma API existente sem comprometer a separação de camadas. Juntos, mostram tanto fundamentos de orientação a objetos quanto arquitetura de sistemas com IA aplicada.

## Stack

Java puro, Spring Boot, Spring AI, Spring Data JPA, MySQL, Gradle, JUnit 5, Docker Compose.

## Estrutura

dio-java-challenges/ contém as pastas bytebank-design-patterns/ e budget-voice-ai/. Cada projeto tem seu próprio README com instruções específicas de execução e testes.

---
