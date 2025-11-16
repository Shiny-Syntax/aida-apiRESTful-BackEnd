# Aida

Backend REST em Spring Boot para gerenciar colaboradores, agendas e registros diários.

> Rápido & direto: documentação Swagger integrada e banco H2 em memória para desenvolvimento.

---

## Sumário

- Requisitos
- Quick Start
- Swagger / OpenAPI (uso recomendado)
- Banco H2 (console e como usar)
- Endpoints principais

---

## Requisitos

- Java 24 (propriedade `java.version` em `pom.xml`).
- Maven (ou use o wrapper `mvnw.cmd` no Windows).

Dependências principais estão no `pom.xml` (Spring Boot, JPA, Validation, H2 e springdoc-openapi).

---

## Quick Start (Windows)

1. Rodar testes:

```cmd
mvnw.cmd test
```

2. Iniciar a aplicação:

```cmd
mvnw.cmd spring-boot:run
```

3. Acesse a API em `http://localhost:8080`.

Gerar JAR e executar:

```cmd
mvnw.cmd package
java -jar target\aida-0.0.1-SNAPSHOT.jar
```

---

## Swagger / OpenAPI (documentação e teste de endpoints)

A forma mais rápida de explorar e testar a API é via Swagger UI (integrado com `springdoc`):

- **Swagger UI:** `http://localhost:8080/swagger-ui.html` (ou `/swagger-ui/index.html`)
- **OpenAPI JSON:** `http://localhost:8080/v3/api-docs`

Como usar a UI do Swagger:

1. Abra a URL do Swagger no navegador.
2. Expanda o controller desejado (Colaborador, Agenda, RegistroDiario).
3. Veja os modelos (request/response), parâmetros e códigos HTTP esperados.
4. Use `Try it out` para enviar requests diretamente e inspecionar respostas.

Por que priorizar o Swagger:

- Documentação viva e sempre atualizada pelo código.
- Interface para testar sem precisar de cliente HTTP externo.
- Mostra exemplos de schema e validação automática dos campos.

---

## Banco de dados (H2) — desenvolvimento

A aplicação vem configurada com H2 em memória (ver `src/main/resources/application.properties`).

- **JDBC URL:** `jdbc:h2:mem:aidadb`
- **H2 Console:** `http://localhost:8080/h2-console`
- **Usuário:** `sa` (sem senha por padrão)

No H2 Console cole a JDBC URL e conecte para inspecionar tabelas e dados. Atenção: o banco é volátil (dados perdidos ao reiniciar) porque o schema é recriado por `spring.jpa.hibernate.ddl-auto=create`.

---

## Endpoints principais

Base: `http://localhost:8080`

- **Colaborador** — `/api/colaboradores`
  - `GET /api/colaboradores` — listar
  - `GET /api/colaboradores/{cpf}` — obter por CPF
  - `POST /api/colaboradores` — criar
  - `PUT /api/colaboradores/{cpf}` — atualizar
  - `DELETE /api/colaboradores/{cpf}` — deletar

- **Agenda** — `/api/agenda`
  - `GET /api/agenda` — listar
  - `GET /api/agenda/{id}` — obter por id
  - `POST /api/agenda` — criar (requer `colaboradorCpf`)
  - `PUT /api/agenda/{id}` — atualizar (requer `colaboradorCpf`)
  - `DELETE /api/agenda/{id}` — deletar

- **Registro Diário** — `/api/registros`
  - `GET /api/registros` — listar
  - `GET /api/registros/{id}` — obter por id
  - `POST /api/registros` — criar (requer `colaboradorCpf`)
  - `PUT /api/registros/{id}` — atualizar (requer `colaboradorCpf`)
  - `DELETE /api/registros/{id}` — deletar

---

## Testes

Execute a suíte de testes com:

```cmd
mvnw.cmd test
```

---

- Projeto mantido por Shiny-Syntax
