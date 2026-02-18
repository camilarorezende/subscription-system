# Subscription System

Sistema backend para gerenciamento de assinaturas recorrentes, permitindo que usuários assinem planos com cobrança semanal, mensal ou anual.

---

## Funcionalidades

- Cadastro de usuários
- Cadastro de planos
- Criação de assinaturas
- Cancelamento de assinaturas
- Controle de status (ATIVO, CANCELADO, EXPIRADO)
- Cálculo do total de assinaturas ativas por usuário
- Preparado para implementação futura de cobrança automática (Scheduler)
  
---

 ## Regras de Negócio

- Usuário pode ter várias assinaturas
- Usuário não pode ter duas assinaturas ativas para o mesmo plano
- Assinatura ativa não pode ser deletada
- Apenas assinaturas ativas podem ser canceladas
- Cancelamento define a data de término automaticamente
- Próxima cobrança é calculada com base no ciclo do plano (WEEKLY, MONTHLY, YEARLY)
- Valores monetários utilizam BigDecimal
  
---

## Arquitetura

O projeto segue arquitetura em camadas:

- Controller → Recebe requisições HTTP
- Service → Contém regras de negócio
- Repository → Comunicação com banco de dados
- Entity → Representação das tabelas
  
---

## Tecnologias Utilizadas

- Java 17+
- Spring Boot
- Spring Data JPA
- Hibernate
- ModelMapper
- Maven
- PostgreSQL
