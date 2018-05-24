create user soboru identified by opet;
grant connect, resource to soboru;
connect soboru/opet

----------------------------------

CREATE TABLE roles (
    id NUMBER(11) NOT NULL,
    nome VARCHAR2(80) NOT NULL,
    is_admin NUMBER(1) NOT NULL,
    CONSTRAINT role_pk PRIMARY KEY (id),
    CONSTRAINT role_nome_unique UNIQUE (nome)
);

CREATE SEQUENCE role_seq
    INCREMENT BY 1
    START WITH 1
    NOCACHE;

CREATE TABLE usuarios (
    id NUMBER(11) NOT NULL,
    nome VARCHAR2(80) NOT NULL,
    email VARCHAR2(80) NOT NULL,
    senha VARCHAR2(80) NOT NULL,
    nasc DATE NOT NULL,
    sexo NUMBER(1) NOT NULL, -- 1 - Masculino, 2 - Feminino, 3 - Indefinido
    id_role NUMBER(11) NOT NULL,
    notificacao_email NUMBER(1) NOT NULL,
    avatar_path VARCHAR2(80) NULL,
    CONSTRAINT usuario_pk PRIMARY KEY (id),
    CONSTRAINT usuario_email_unique UNIQUE (email)
);

CREATE SEQUENCE usuario_seq
    INCREMENT BY 1
    START WITH 1
    NOCACHE;

CREATE TABLE medidas (
    id NUMBER(11) NOT NULL,
    nome VARCHAR2(80) NOT NULL,
    abreviacao VARCHAR2(80) NOT NULL,
    CONSTRAINT medida_pk PRIMARY KEY (id),
    CONSTRAINT medida_nome_unique UNIQUE (nome)
);

CREATE SEQUENCE medida_seq
    INCREMENT BY 1
    START WITH 1
    NOCACHE;    

CREATE TABLE ingredientes (
    id NUMBER(11) NOT NULL,
    nome VARCHAR2(80) NOT NULL,
    CONSTRAINT ingrediente_pk PRIMARY KEY (id),
    CONSTRAINT ingrediente_nome_unique UNIQUE (nome)
);

CREATE SEQUENCE ingrediente_seq
    INCREMENT BY 1
    START WITH 1
    NOCACHE;

CREATE TABLE categorias (
    id NUMBER(11) NOT NULL,
    nome VARCHAR2(80) NOT NULL,
    id_super_categoria NUMBER(11) NULL,
    selecionavel NUMBER(1) NOT NULL,
    slug VARCHAR2(80) NOT NULL,
    CONSTRAINT categoria_pk PRIMARY KEY (id),
    CONSTRAINT categoria_nome_unique UNIQUE (nome)
);

CREATE SEQUENCE categoria_seq
    INCREMENT BY 1
    START WITH 1
    NOCACHE;

CREATE TABLE utensilios (
    id NUMBER(11) NOT NULL,
    nome VARCHAR2(80) NOT NULL,
    CONSTRAINT utensilio_pk PRIMARY KEY (id),
    CONSTRAINT utensilio_nome_unique UNIQUE (nome)
);

CREATE SEQUENCE utensilio_seq
    INCREMENT BY 1
    START WITH 1
    NOCACHE;

CREATE TABLE receitas (
    id NUMBER(11) NOT NULL,
    nome VARCHAR2(80) NOT NULL,
    id_categoria NUMBER(11) NOT NULL,
    id_usuario NUMBER(11) NOT NULL,
    porcao NUMBER(11) NOT NULL,
    tempo_preparo DECIMAL(5,2) NOT NULL,
    modo_preparo CLOB NOT NULL,
    img_path VARCHAR2(80),
    pontuacao_media DECIMAL(5,2) NOT NULL,
    views NUMBER(11) NOT NULL,
    favs NUMBER(11) NOT NULL,
    slug VARCHAR2(80) NOT NULL,
    aprovado NUMBER(1) NOT NULL,
    CONSTRAINT receita_pk PRIMARY KEY (id),
    CONSTRAINT receita_cat_fk FOREIGN KEY (id_categoria) REFERENCES categorias(id),
    CONSTRAINT receita_usr_fk FOREIGN KEY (id_usuario) REFERENCES usuarios(id)
);

CREATE SEQUENCE receita_seq
    INCREMENT BY 1
    START WITH 1
    NOCACHE;

CREATE TABLE receitas_ingredientes (
    id NUMBER(11) NOT NULL,
    id_receita NUMBER(11) NOT NULL,
    id_ingrediente NUMBER(11) NOT NULL,
    id_medida NUMBER(11) NOT NULL,
    sub_sessao VARCHAR2(80) NULL,
    qty DECIMAL(5,2) NOT NULL,
    CONSTRAINT rec_ing_pk PRIMARY KEY(id),
    CONSTRAINT rec_ing_rec_fk FOREIGN KEY (id_receita) REFERENCES receitas(id),
    CONSTRAINT rec_ing_ing_fk FOREIGN KEY (id_ingrediente) REFERENCES ingredientes(id),
    CONSTRAINT rec_ing_med_fk FOREIGN KEY (id_medida) REFERENCES medidas(id)
);

CREATE SEQUENCE receita_ingrediente_seq
    INCREMENT BY 1
    START WITH 1
    NOCACHE;

CREATE TABLE receitas_utensilios (
    id_receita NUMBER(11) NOT NULL,
    id_utensilio NUMBER(11) NOT NULL,
    CONSTRAINT rec_utens_pk PRIMARY KEY (id_receita, id_utensilio),
    CONSTRAINT rec_utens_rec_fk FOREIGN KEY (id_receita) REFERENCES receitas(id),
    CONSTRAINT rec_utens_uten_fk FOREIGN KEY (id_utensilio) REFERENCES utensilios(id)
);

CREATE TABLE reports (
    id_usuario NUMBER(11) NOT NULL,
    id_receita NUMBER(11) NOT NULL,
    CONSTRAINT reports_pk PRIMARY KEY (id_usuario, id_receita),
    CONSTRAINT reports_usr_fk FOREIGN KEY (id_usuario) REFERENCES usuarios(id),
    CONSTRAINT reports_rec_fk FOREIGN KEY (id_receita) REFERENCES receitas(id)
);

CREATE TABLE receitas_fav (
    id_receita NUMBER(11) NOT NULL,
    id_usuario NUMBER(11) NOT NULL,
    CONSTRAINT receita_fav_pk PRIMARY KEY (id_receita, id_usuario),
    CONSTRAINT rec_fav_usr_fk FOREIGN KEY (id_usuario) REFERENCES usuarios(id),
    CONSTRAINT rec_fav_rec_fk FOREIGN KEY (id_receita) REFERENCES receitas(id)
);

CREATE TABLE comentarios (
    id NUMBER(11) NOT NULL,
    id_receita NUMBER(11) NOT NULL,
    id_usuario NUMBER(11) NOT NULL,
    body CLOB NOT NULL,
    CONSTRAINT comentarios_pk PRIMARY KEY (id),
    CONSTRAINT comentarios_usr_fk FOREIGN KEY (id_usuario) REFERENCES usuarios(id),
    CONSTRAINT comentarios_rec_fk FOREIGN KEY (id_receita) REFERENCES receitas(id)
);

CREATE SEQUENCE comentario_seq
    INCREMENT BY 1
    START WITH 1
    NOCACHE;

CREATE TABLE pontuacoes (
    id_receita NUMBER(11) NOT NULL,
    id_usuario NUMBER(11) NOT NULL,
    qty NUMBER(1) NOT NULL,
    CONSTRAINT pont_rec_pk PRIMARY KEY (id_receita, id_usuario),
    CONSTRAINT pont_rec_rec_fk FOREIGN KEY (id_receita) REFERENCES receitas(id),
    CONSTRAINT pont_rec_usr_fk FOREIGN KEY (id_usuario) REFERENCES usuarios(id)
);

----------------------------------

insert into roles(id, nome, is_admin) values(role_seq.NEXTVAL, 'Admin', 1);
insert into roles(id, nome, is_admin) values(role_seq.NEXTVAL, 'Usuario', 0);

insert into usuarios(id, nome, email, senha, nasc, sexo, id_role, notificacao_email, avatar_path) 
	values(usuario_seq.NEXTVAL, 'Admin Teste', 'admin@teste.com', '123456', TO_DATE('28-02-1990', 'DD_MM_YYYY'), 3, 1, 1, null);
insert into usuarios(id, nome, email, senha, nasc, sexo, id_role, notificacao_email, avatar_path) 
	values(usuario_seq.NEXTVAL, 'Usuario Teste', 'teste@teste.com', '123456', TO_DATE('07-11-1988', 'DD_MM_YYYY'), 3, 2, 1, null);

----------------------------------

commit;
