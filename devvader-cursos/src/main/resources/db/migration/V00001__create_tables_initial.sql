CREATE TABLE IF NOT EXISTS devvader-cursos.formacao.cursos(
    id INTEGER NOT NULL,
    nome VARCHAR(150) NOT NULL CONSTRAINT nome_n_vazio CHECK(nome <> ''),
    status VARCHAR(50) NOT NULL CONSTRAINT status_limitado CHECK(status IN('CURSANDO', 'CONCLUIDO', 'INCOMPLETO')),
    data_inicio DATE NOT NULL CONSTRAINT data_n_vazia CHECK(data_inicio <> ''),
    data_fim DATE WITH DEFAULT NULL,
    cliente_id INTEGER NOT NULL,
    CONSTRAINT pk_id PRIMARY KEY(id)
) ENGINE=InnoDB DEFAULT CHARSET=UTF8;

CREATE TABLE IF NOT EXISTS devvader-cursos.formacao.instituicoes(
    id INTEGER NOT NULL CONSTRAINT PRIMARY KEY,
    nome VARCHAR(150) NOT NULL CONSTRAINT nome_n_vazio CHECK(nome <> ''),
    sigla VARCHAR(10) NOT NULL,
    estado CHAR(2) NOT NULL CONSTRAINT estado_limitado CHECK(estado IN('RS', 'SC', 'PR', 'SP', 'RJ', 'MG', 'ES', 'MS',
    'MT', 'GO', 'DF', 'AC', 'AM', 'RR', 'RO', 'TO', 'AP', 'PA', 'PI', 'BA', 'RN', 'CE', 'MA', 'PB', 'PE', 'AL', 'SE')),
    curso_id INTEGER NOT NULL,
    CONSTRAINT fk_curso_id FOREIGN KEY(curso_id) REFERENCES cursos(id) ON DELETE CASCADE ON UPDATE CASCADE;
) ENGINE=InnoDB DEFAULT CHARSET=UTF8;

CREATE TABLE IF NOT EXISTS devvader-cursos.formacao.validacoes(
    id INTEGER NOT NULL,
    link_certificado VARCHAR(250) WITH DEFAULT NULL,
    registro_certificado VARCHAR(250) WITH DEFAULT NULL,
    curso_id INTEGER NOT NULL,
    CONSTRAINT pk_id PRIMARY KEY(id),
    CONSTRAINT fk_curso_id FOREIGN KEY(curso_id) REFERENCES cursos(id) ON DELETE CASCADE ON DELETE CASCADE ON UPDATE CASCADE;
) ENGINE=InnoDB DEFAULT CHARSET=UTF8;


