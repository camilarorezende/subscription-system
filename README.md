# Subscription System

API REST desenvolvida em Java 17 e Spring Boot para gerenciamento de usuários, planos e assinaturas recorrentes.

O sistema permite cadastrar usuários e planos, criar assinaturas vinculadas a diferentes ciclos de cobrança e controlar o status das assinaturas.

Observação: O projeto calcula a próxima data de cobrança, mas ainda não realiza cobranças reais nem possui integração com gateways de pagamento.

---

## 📌 Funcionalidades


### 👤 Usuários

- [x] Cadastro de usuários
- [x] Listagem de usuários
- [x] Busca de usuário por ID
- [x] Atualização de dados
- [x] Exclusão de usuários
- [x] Validação básica de senha
- [x] Proteção contra exclusão de usuários com assinatura ativa

  
### 📋 Planos

- [x] Cadastro de planos
- [x] Listagem de planos
- [x] Busca de plano por ID
- [x] Atualização de planos
- [x] Exclusão de planos
- [x] Definição de preço, nível e ciclo de cobrança
- [x] Ciclo de cobrança semanal
- [x] Ciclo de cobrança mensal
- [x] Ciclo de cobrança anual
- [x] Proteção contra exclusão de planos com assinaturas ativas

### 🔄 Assinaturas

- [x] Criação de assinaturas
- [x] Listagem de assinaturas
- [x] Busca de assinatura por ID
- [x] Atualização do status
- [x] Cancelamento de assinaturas
- [x] Exclusão de assinaturas canceladas ou expiradas
- [x] Associação entre usuários e planos
- [x] Cálculo da próxima cobrança com base no ciclo do plano
- [x] Controle de status:
  - `ATIVO`
  - `CANCELADO`
  - `EXPIRADO`

---

## 📐 Regras de Negócio

### Usuários

- Usuários podem possuir várias assinaturas.
- Um usuário não pode possuir duas assinaturas ativas para o mesmo plano.
- O usuário precisa existir antes da criação de uma assinatura.
- Usuários com assinaturas ativas não podem ser excluídos.
- O e-mail do usuário deve ser único.

### Planos

- O plano precisa existir antes da criação de uma assinatura.
- Planos com assinaturas ativas não podem ser excluídos.
- O nome do serviço do plano deve ser único.
- Valores monetários utilizam `BigDecimal`.

### Assinaturas

- Novas assinaturas são criadas com status `ATIVO`.
- A data de início é definida automaticamente como a data atual.
- A próxima cobrança é calculada conforme o ciclo do plano:
  - `WEEKLY`: 7 dias depois
  - `MONTHLY`: 1 mês depois
  - `YEARLY`: 1 ano depois
- Apenas assinaturas ativas podem ser canceladas.
- O cancelamento altera o status para `CANCELADO`.
- O cancelamento define automaticamente a data de término.
- Assinaturas ativas não podem ser excluídas.
  
---

## 🏗️ Arquitetura

O projeto segue arquitetura em camadas:

- Controller → recebe e responde às requisições HTTP.
- Service → concentra as regras de negócio.
- Repository → realiza a comunicação com o banco de dados.
- Model → representa as entidades persistidas.
- DTO → controla os dados recebidos e enviados pela API.
- Exception → trata erros específicos da aplicação.
- Configuration → centraliza configurações, como o ModelMapper.

---

## Tecnologias Utilizadas

- Java 17+
- Spring Boot
- Spring Data JPA
- Hibernate
- ModelMapper
- Maven
- Lombok
- PostgreSQL

## Status do Projeto

🚧 Em desenvolvimento

O projeto atualmente possui o gerenciamento de usuários, planos e assinaturas, incluindo as principais regras de negócio relacionadas aos ciclos de cobrança e controle de status.

Próximos passos:

 - Implementar autenticação e autorização
 - Melhorar tratamento global de exceções
 - Adicionar testes unitários
 - Adicionar testes de integração
 - Documentar a API com Swagger/OpenAPI
 - Integrar com gateway de pagamento
 - Implementar processamento de cobranças recorrentes
