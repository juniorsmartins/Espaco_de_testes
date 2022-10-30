CREATE TABLE IF NOT EXISTS cursos
(
    id BIGSERIAL PRIMARY KEY,
    titulo VARCHAR(175) NOT NULL CHECK(titulo <> ''),
    instituicao VARCHAR(175) NOT NULl CHECK(titulo <> ''),
    data_conclusao DATE NOT NULL,
    carga_horaria NUMERIC NOT NULL CHECK(carga_horaria > 0),
    preco NUMERIC CHECK(preco >= 0) DEFAULT 0,
    link VARCHAR(255),
    assunto_id INTEGER NOT NULL,
    CONSTRAINT fk_assunto_curso FOREIGN KEY (assunto_id) REFERENCES assuntos(id) ON UPDATE CASCADE ON DELETE RESTRICT
);

