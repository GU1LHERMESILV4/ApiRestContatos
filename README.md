# ☎ API Rest para Controle de Contatos

## Índice  


1. [Introdução](#introdução)  
2. [Tecnologias Utilizadas](#tecnologias-utilizadas)  
3. [Funcionalidades](#funcionalidades)  
   - [CRUD de Pessoas](#1-crud-de-pessoas)  
   - [CRUD de Contatos](#2-crud-de-contatos)  
4. [Modelagem de Dados](#modelagem-de-dados)  
   - [Pessoa](#pessoa)  
   - [Contato](#contato)  
5. [Como Executar o Projeto](#como-executar-o-projeto)  
   - [Pré-requisitos](#pré-requisitos)  
   - [Passos](#passos)  
6. [Configuração do Banco de Dados](#configuração-do-banco-de-dados)  
7. [Execução do Projeto](#execução-do-projeto)  
8. [Documentação da API](#documentação-da-api)  
9. [Testes](#testes)  
   - [Exemplo de cURL para criar uma nova Pessoa](#exemplo-de-curl-para-criar-uma-nova-pessoa)  
   
## Introdução

Este projeto é uma API REST desenvolvida em Java com Spring Boot, com o objetivo de gerenciar um sistema de cadastro de Pessoas e seus respectivos Contatos. A API permite operações CRUD (Criar, Ler, Atualizar e Deletar) tanto para Pessoas quanto para Contatos.

## Tecnologias Utilizadas

- **Java 21**
- **Spring Boot 3.4.2**
- **JPA/Hibernate**
- **Banco de Dados**: MySQL, MariaDB, PostgreSQL ou H2
- **Swagger (OpenAPI)** para documentação da API
- **Postman** para testes de API

## Funcionalidades

A API implementa as seguintes funcionalidades:

### 1. CRUD de Pessoas:
- **POST /api/pessoas**: Cria uma nova Pessoa.
- **GET /api/pessoas/{id}**: Retorna os dados de uma Pessoa por ID.
- **GET /api/pessoas/maladireta/{id}**: Retorna os dados de uma Pessoa para mala direta (DTO com campos selecionados).
- **GET /api/pessoas**: Lista todas as Pessoas.
- **PUT /api/pessoas/{id}**: Atualiza os dados de uma Pessoa.
- **DELETE /api/pessoas/{id}**: Deleta uma Pessoa por ID.

### 2. CRUD de Contatos:
- **POST /api/contatos**: Adiciona um novo Contato a uma Pessoa.
- **GET /api/contatos/{id}**: Retorna os dados de um Contato por ID.
- **GET /api/contatos/pessoa/{idPessoa}**: Lista todos os Contatos de uma Pessoa.
- **PUT /api/contatos/{id}**: Atualiza um Contato existente.
- **DELETE /api/contatos/{id}**: Deleta um Contato por ID.

## Modelagem de Dados

### Pessoa:
- **ID** (único, não pode ser nulo)
- **Nome** (não pode ser nulo)
- **Endereço** (pode ser nulo)
- **CEP** (pode ser nulo)
- **Cidade** (pode ser nulo)
- **UF** (pode ser nulo)

### Contato:
- **ID** (único, não pode ser nulo)
- **Tipo Contato** (inteiro) [0 - Telefone, 1 - Celular]
- **Contato** (não pode ser nulo)
- Relacionamento com a entidade **Pessoa** (OneToMany, ManyToOne)

## Como Executar o Projeto

### Pré-requisitos

- **JDK 21** ou superior
- **MySQL, MariaDB, PostgreSQL ou H2** (certifique-se de que o banco de dados está configurado corretamente)
- **Maven** (ou Gradle) para gerenciamento de dependências

### Passos

1. Clone o repositório:
   ```bash
   git clone https://github.com/GU1LHERMESILV4/ApiRestContatos.git
   cd ApiRestContatos
   ```

   Importe o projeto em sua IDE preferida como IntelliJ(Recomendada) ou Eclipse.

## Configuração do Banco de Dados

Configure as credenciais do banco de dados no arquivo `application.properties`:

```properties
spring.datasource.url=jdbc:h2:mem:pessoa
spring.datasource.driver-class-name=org.h2.Driver
spring.datasource.username=sa
spring.datasource.password=
spring.datasource.hikari.minimum-idle=5
spring.datasource.hikari.maximum-pool-size=10
spring.datasource.hikari.idle-timeout=30000
spring.datasource.hikari.connection-timeout=30000
spring.datasource.hikari.max-lifetime=600000
spring.datasource.hikari.auto-commit=true
spring.datasource.hikari.transaction-isolation=TRANSACTION_READ_COMMITTED
spring.jpa.show-sql=true
spring.jpa.hibernate.ddl-auto=update
spring.h2.console.enabled=true
spring.h2.console.path=/h2-console
spring.jpa.defer-datasource-initialization=true
spring.jpa.open-in-view=true
```

## Execução do Projeto

Compile o projeto e execute:

```bash
mvn spring-boot:run
```

Acesse a API em [http://localhost:8080](http://localhost:8080).

## Documentação da API

A documentação da API está disponível utilizando o Swagger. Após executar o projeto, você pode acessar a interface do Swagger em:

[Swagger UI](http://localhost:8080/swagger-ui.html)

## Testes

Para garantir que tudo esteja funcionando corretamente, você pode utilizar ferramentas como Postman ou cURL para testar os endpoints da API.

### Exemplo de cURL para criar uma nova Pessoa:

```bash
curl -X POST "http://localhost:8080/api/pessoas" \
     -H "Content-Type: application/json" \
     -d '{"nome":"João", "endereco":"Rua A, 1", "cep":"11111-000", "cidade":"Cidade", "uf":"SP"}'
```

