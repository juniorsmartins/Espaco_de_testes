CREATE TABLE IF NOT EXISTS assuntos
(
    id INTEGER NOT NULL PRIMARY KEY,
    assunto VARCHAR(75) NOT NULL UNIQUE CHECK(assunto <> '')
);
