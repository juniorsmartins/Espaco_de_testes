CREATE TABLE IF NOT EXISTS assuntos
(
    id BIGSERIAL PRIMARY KEY,
    tema VARCHAR(75) NOT NULL UNIQUE CHECK(tema <> '')
);

