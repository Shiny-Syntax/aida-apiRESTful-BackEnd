CREATE SEQUENCE seq_id_atividade
    START WITH 1
    CACHE 1;

CREATE SEQUENCE seq_id_registro
    START WITH 1
    CACHE 1;

CREATE TABLE colaborador (
    cpf_colaborador BIGINT PRIMARY KEY,
    nome VARCHAR(255) NOT NULL,
    email VARCHAR(255),
    telefone VARCHAR(50),
    data_nascimento DATE,
    cargo VARCHAR(255),
    modalidade VARCHAR(20),
    data_admissao DATE,
    problema_saude TEXT DEFAULT 'NÃO POSSUI PROBLEMAS DE SAÚDE',
    medicamento_uso_diario TEXT DEFAULT 'NÃO FAZ USO DE MEDICAMENTO CONTROLADO'
);

-- ===================================
-- Tabela Agenda
-- ===================================
CREATE TABLE agenda (
    id_atividade BIGINT PRIMARY KEY DEFAULT nextval('seq_id_atividade'),
    tipo VARCHAR(255) NOT NULL,
    descricao TEXT,
    data_hora TIMESTAMP NOT NULL,
    data_conclusao DATE NOT NULL,
    prioridade VARCHAR(20) NOT NULL CHECK (prioridade IN ('ALTA','BAIXA','MÉDIA','URGENTE')),
    plataforma VARCHAR(255),
    status VARCHAR(20) NOT NULL CHECK (status IN ('AGENDADO','EM ANDAMENTO','CANCELADO','CONCLUÍDO')),
    colaborador_cpf BIGINT NOT NULL,
    CONSTRAINT fk_agenda_colaborador FOREIGN KEY (colaborador_cpf) REFERENCES colaborador(cpf_colaborador)
);

-- ===================================
-- Tabela RegistroDiario
-- ===================================
CREATE TABLE registro_diario (
    id_registro BIGINT PRIMARY KEY DEFAULT nextval('seq_id_registro'),
    data_registro DATE NOT NULL,
    escala_emocional DOUBLE PRECISION,
    tempo_tela INT,
    pausas_realizadas INT,
    observacoes_colaborador VARCHAR(400),
    observacoes_aida TEXT,
    cpf_colaborador BIGINT NOT NULL,
    CONSTRAINT fk_registro_colaborador FOREIGN KEY (cpf_colaborador) REFERENCES colaborador(cpf_colaborador)
);
