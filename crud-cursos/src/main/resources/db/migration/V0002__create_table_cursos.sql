CREATE TABLE IF NOT EXISTS cursos
(
    id INTEGER NOT NULL PRIMARY KEY,
    titulo VARCHAR(175) NOT NULL CHECK(titulo <> ''),
    instituicao VARCHAR(175) NOT NULl CHECK(titulo <> ''),
    data_conclusao DATE NOT NULL,
    carga_horaria NUMERIC NOT NULL,
    preco NUMERIC CHECK(preco >= 0) DEFAULT 0,
    link VARCHAR(255),
    assunto_id INTEGER NOT NULL,
    CONSTRAINT fk_assunto FOREIGN KEY (assunto_id) REFERENCES assuntos(id)
);

