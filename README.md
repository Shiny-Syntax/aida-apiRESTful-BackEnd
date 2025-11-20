# Sobre
Este projeto é uma **API RESTful** projetada para **gerenciar** colaboradores, agendas e registros diários, oferecendo **operações completas de CRUD** e **processamento interno de dados**. Esta versão da API é voltada para uso em **ambiente de desenvolvimento e testes**, garantindo que as informações sejam **validadas**, **tratadas** e **armazenadas** corretamente no banco de dados.

Se você deseja consultar a **API de Testes**, utilizada em desenvolvimento e integrada à um banco de memória temporária, clique [aqui](https://github.com/Shiny-Syntax/aida-apiRESTful-BackEnd).


## Conheça o projeto

### Tecnologias Utilizadas
- Spring Boot Web
- Spring Boot Data JPA
- Spring Boot Validation
- springdoc-openapi (Swagger UI)


### Funcionalidades Principais


## Endpoints principais


---

---

## Troubleshooting rápido

- Confira a versão do JDK (`java -version`) se a aplicação não iniciar.
- Erros de validação retornam 422 com detalhes — confira o campo e o formato no Swagger.
- Para inspecionar dados: H2 Console em `/h2-console`.


```mermaid
graph LR
  %% ===== CLIENT =====
  subgraph Client["Client"]
    direction TB
    A["Browser / Client"]
  end

  %% ===== API =====
  subgraph API["Application Layer"]
    direction TB
    C["Controller"]
    D["DTOs (contracts)"]
    M["Mappers"]
    S["Service"]
    R["Repository"]
    E["Entities (domain)"]
    EX["GlobalExceptionHandler"]
  end

  %% ===== INFRA =====
  subgraph Infra["Infrastructure"]
    direction TB
    DB[("Database")]
    SEC["Security Filters"]
    CFG["Config / Beans"]
  end

  %% ===== RELATIONSHIPS =====
  A -->|HTTP| SEC
  SEC --> C
  C -->|validates| D
  C -->|calls| S
  %% mappers are used by controllers and services to transform DTO <-> Entity
  D <--> M
  M <--> E
  S -->|uses| R
  R --> DB
  %% exception handler intercepts exceptions from controllers, services and repositories
  EX -.->|intercepts| C
  EX -.->|intercepts| S
  EX -.->|intercepts| R
  CFG -->|injects| C
  CFG -->|injects| S
  CFG -->|configures| R

  %% ===== STYLES =====
  classDef client fill:#1E90FF,stroke:#ffffff,stroke-width:1px;
  classDef api fill:#C71585,stroke:#ffffff,stroke-width:1px;
  classDef infra fill:#228B22,stroke:#ffffff,stroke-width:1px;
  
  class A client;
  class C,D,M,S,R,E,EX api;
  class CFG,DB,SEC infra;
```