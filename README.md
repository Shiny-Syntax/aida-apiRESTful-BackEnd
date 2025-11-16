# Aida

API backend de exemplo em Spring Boot para gerenciamento de colaboradores, agenda e registros diários.

## Status
- Versão: `0.0.1-SNAPSHOT`

## Requisitos
- Java: `24` (definido em `pom.xml` - recomenda-se usar Java 24+). O terminal de desenvolvimento pode mostrar outras versões, mas siga a propriedade `java.version`.
- Maven (ou use o wrapper `mvnw.cmd` no Windows)

## Dependências principais
- Spring Boot Web
- Spring Boot Data JPA
- Spring Boot Validation
- H2 (runtime, para desenvolvimento/in-memory)
- Oracle JDBC (`ojdbc11`) (só se configurar datasource)
- springdoc-openapi (Swagger UI)

As dependências estão no `pom.xml`.

## Como rodar (desenvolvimento)

1. Build e testa localmente (Windows CMD):
```cmd
mvnw.cmd test
```

2. Rodar a aplicação:
```cmd
mvnw.cmd spring-boot:run
```

ou gerar jar e executar:
```cmd
mvnw.cmd package
java -jar target\aida-0.0.1-SNAPSHOT.jar
```

A aplicação por padrão usa H2 em memória e cria o schema automaticamente (`spring.jpa.hibernate.ddl-auto=create`).

## Configurações
- Arquivo de propriedades: `src/main/resources/application.properties`.
- Porta padrão: `8080` (padrão do Spring Boot). Para alterar, defina `server.port` em `application.properties`.
- H2 Console: habilitado em `/h2-console` (veja `application.properties`).

## Swagger / OpenAPI
- A integração `springdoc-openapi-starter-webmvc-ui` está inclusa no `pom.xml`.
- UI do Swagger: `http://localhost:8080/swagger-ui.html` (ou `http://localhost:8080/swagger-ui/index.html`).
- OpenAPI JSON: `http://localhost:8080/v3/api-docs`.

## Endpoints principais

Base: `http://localhost:8080`

- Colaborador (`/api/colaboradores`)
  - GET `/api/colaboradores` — listar todos
  - GET `/api/colaboradores/{cpf}` — obter por CPF
  - POST `/api/colaboradores` — criar
  - PUT `/api/colaboradores/{cpf}` — atualizar
  - DELETE `/api/colaboradores/{cpf}` — deletar

- Agenda (`/api/agenda`)
  - GET `/api/agenda` — listar todos
  - GET `/api/agenda/{id}` — obter por id
  - POST `/api/agenda` — criar (requer `colaboradorCpf`)
  - PUT `/api/agenda/{id}` — atualizar (requer `colaboradorCpf`)
  - DELETE `/api/agenda/{id}` — deletar

- Registro Diário (`/api/registros`)
  - GET `/api/registros` — listar todos
  - GET `/api/registros/{id}` — obter por id
  - POST `/api/registros` — criar (requer `colaboradorCpf`)
  - PUT `/api/registros/{id}` — atualizar (requer `colaboradorCpf`)
  - DELETE `/api/registros/{id}` — deletar

## Exemplos de payloads (JSON)

- Criar `Colaborador`:
```json
{
  "cpf": "55566677788",
  "nome": "Raquel Silva",
  "email": "raquel@example.com",
  "telefone": "+5511991112222",
  "dataNascimento": "1991-05-10",
  "cargo": "Analista",
  "modalidade": "PRESENCIAL",
  "dataAdmissao": "2023-01-15",
  "problemaSaude": "Nenhum",
  "medicamentoUsoDiario": "Nenhum"
}
```

- Criar `Agenda`:
```json
{
  "tipo": "Reunião",
  "descricao": "Alinhamento semanal",
  "dataHora": "2026-01-01T10:00:00",
  "prioridade": "ALTA",
  "plataforma": "Zoom",
  "status": "AGENDADO",
  "colaboradorCpf": "11122233344"
}
```

- Criar `RegistroDiario`:
```json
{
  "dataRegistro": "2026-01-01",
  "escalaEmocional": 5,
  "tempoTela": 3,
  "pausasRealizadas": 2,
  "observacoesColaborador": "Tudo certo",
  "observacoesAIDA": "Sem observações",
  "colaboradorCpf": "11122233344"
}
```

Curl de exemplo (criar colaborador):
```cmd
curl -X POST http://localhost:8080/api/colaboradores -H "Content-Type: application/json" -d @colaborador.json
```

## Seeds / Dados iniciais
O `DataLoader` (classe `src/main/java/com/shinysyntax/aida/aida/config/DataLoader.java`) cria seeds em memória no startup. CPFs e exemplos incluídos:

- `11122233344` — Maria Silva (Analista)
- `22233344455` — Joao Pereira (Desenvolvedor)
- `33344455566` — Ana Costa (Designer) — `problemaSaude`: `CID 10 F32: depressão`
- `44455566677` — Carlos Souza (Gerente) — `medicamentoUsoDiario`: `102436091: fluexetina`

Agendas e registros também são populados por `DataLoader` e as respostas incluem `colaboradorCpf` e `colaboradorNome`.

## H2 Console
- Acesse `http://localhost:8080/h2-console`.
- JDBC URL (por padrão): `jdbc:h2:mem:aidadb`
- Usuário: `sa` — sem senha por padrão.

## Mudar para Oracle (runtime)
- Ajuste `spring.datasource.*` em `application.properties` para apontar para a instância Oracle e remova/altere a dependência H2.
- A dependência `ojdbc11` já está declarada com `scope=runtime` — apenas configure URL/credentials.

## Logging e troubleshooting
- Para ver SQL: `spring.jpa.show-sql=true` (já habilitado)
- Se houver problemas com a versão do JDK, ajuste a propriedade `java.version` no `pom.xml` e use o JDK apropriado.

## Testes
- Execute com:
```cmd
mvnw.cmd test
```

## Contribuição
- Padronize commits, escreva testes para funcionalidades novas e siga as convenções do projeto.

## Licença
- (Adicione aqui a licença do projeto se aplicável)

## Contato
- Projeto mantido por Shiny-Syntax
