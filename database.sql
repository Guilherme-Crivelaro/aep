CREATE TYPE TipoUsuario AS ENUM(
    'BENEFICIARIO',
    'DOADOR'
);

CREATE TYPE StatusLivro AS ENUM(
    'DISPONIVEL',
    'RESERVADO',
    'DOADO'
);

CREATE TYPE StatusDoacao AS ENUM(
    'AGENDADA',
    'CONCLUIDA',
    'CANCELADA',
    'DISPONIVEL'
);

CREATE TABLE usuario (
     id BIGINT NOT NULL PRIMARY KEY,
     nome VARCHAR(255) NOT NULL,
     email VARCHAR(255) NOT NULL UNIQUE,
     senha VARCHAR(255) NOT NULL,
     tipo TipoUsuario NOT NULL
);

CREATE TABLE livros (
    id BIGINT NOT NULL PRIMARY KEY,
    titulo VARCHAR(255) NOT NULL,
    autor VARCHAR(255) NOT NULL,
    curso VARCHAR(255) NOT NULL,
    disciplina VARCHAR(255) NOT NULL,
    status StatusLivro NOT NULL,
    doador_id BIGINT NOT NULL,
    CONSTRAINT fk_livros_usuario FOREIGN KEY (doador_id) REFERENCES usuario(id)
);

CREATE TABLE doacoes (
     id BIGINT NOT NULL PRIMARY KEY,
     data_agendamento DATE,
     status_doacao StatusDoacao NOT NULL,
     livro_id BIGINT NOT NULL,
     beneficiario_id BIGINT NOT NULL,
     CONSTRAINT fk_doacoes_livro FOREIGN KEY (livro_id) REFERENCES livros(id),
     CONSTRAINT fk_doacoes_usuario FOREIGN KEY (beneficiario_id) REFERENCES usuario(id)
);