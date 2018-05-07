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
    -- tipo NUMBER(1) NOT NULL,
    nasc DATE NOT NULL,
    sexo NUMBER(1) NOT NULL,
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

CREATE TABLE ingredientes_fav (
    id_ingrediente NUMBER(11) NOT NULL,
    id_usuario NUMBER(11) NOT NULL,
    CONSTRAINT ing_fav_pk PRIMARY KEY (id_ingrediente, id_usuario),
    CONSTRAINT ing_fav_ing_fk FOREIGN KEY (id_ingrediente) REFERENCES ingredientes(id),
    CONSTRAINT ing_fav_usr_fk FOREIGN KEY (id_usuario) REFERENCES usuarios(id)
);

CREATE TABLE ingredientes_exc (
    id_ingrediente NUMBER(11) NOT NULL,
    id_usuario NUMBER(11) NOT NULL,
    CONSTRAINT ing_exc_pk PRIMARY KEY (id_ingrediente, id_usuario),
    CONSTRAINT ing_exc_ing_fk FOREIGN KEY (id_ingrediente) REFERENCES ingredientes(id),
    CONSTRAINT ing_exc_usr_fk FOREIGN KEY (id_usuario) REFERENCES usuarios(id)
);

CREATE TABLE receitas_fav (
    id_receita NUMBER(11) NOT NULL,
    id_usuario NUMBER(11) NOT NULL,
    CONSTRAINT receita_fav_pk PRIMARY KEY (id_receita, id_usuario),
    CONSTRAINT rec_fav_usr_fk FOREIGN KEY (id_usuario) REFERENCES usuarios(id),
    CONSTRAINT rec_fav_rec_fk FOREIGN KEY (id_receita) REFERENCES receitas(id)
);


CREATE TABLE receitas_exc (
    id_receita NUMBER(11) NOT NULL,
    id_usuario NUMBER(11) NOT NULL,
    CONSTRAINT receita_exc_pk PRIMARY KEY (id_receita, id_usuario),
    CONSTRAINT rec_exc_usr_fk FOREIGN KEY (id_usuario) REFERENCES usuarios(id),
    CONSTRAINT rec_exc_rec_fk FOREIGN KEY (id_receita) REFERENCES receitas(id)
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

insert into roles values(role_seq.NEXTVAL, 'Admin', 1);
insert into roles values(role_seq.NEXTVAL, 'Usuario', 0);

insert into usuarios values(usuario_seq.NEXTVAL, 'Admin Teste', 'admin@teste.com', '123456', TO_DATE('28-02-1990', 'DD_MM_YYYY'), 3, 1, 1, null);
insert into usuarios values(usuario_seq.NEXTVAL, 'Usuario Teste', 'teste@teste.com', '123456', TO_DATE('07-11-1988', 'DD_MM_YYYY'), 3, 2, 1, null);

--Ingredientes
--insert into ingredientes values (ingrediente_seq.NEXTVAL,'NOME');

--Ingredientes - Arroz de Lentilha
insert into ingredientes values (ingrediente_seq.NEXTVAL,'agua fervente');
insert into ingredientes values (ingrediente_seq.NEXTVAL,'lentilha');
insert into ingredientes values (ingrediente_seq.NEXTVAL,'alho picado');
insert into ingredientes values (ingrediente_seq.NEXTVAL,'azeite');
insert into ingredientes values (ingrediente_seq.NEXTVAL,'arroz branco');
insert into ingredientes values (ingrediente_seq.NEXTVAL,'sal');

--Ingredientes - Cebola Frita
insert into ingredientes values (ingrediente_seq.NEXTVAL,'cebola');
insert into ingredientes values (ingrediente_seq.NEXTVAL,'farinha de trigo');
insert into ingredientes values (ingrediente_seq.NEXTVAL,'oleo');

--Ingredientes - Torta
insert into ingredientes values (ingrediente_seq.NEXTVAL,'pimentao verde');
insert into ingredientes values (ingrediente_seq.NEXTVAL,'pimenta dedo-de-moca');
insert into ingredientes values (ingrediente_seq.NEXTVAL,'ovo');
insert into ingredientes values (ingrediente_seq.NEXTVAL,'mucarela ralada');
insert into ingredientes values (ingrediente_seq.NEXTVAL,'iogurte natural');
insert into ingredientes values (ingrediente_seq.NEXTVAL,'salsinha');
insert into ingredientes values (ingrediente_seq.NEXTVAL,'fermento em po');
insert into ingredientes values (ingrediente_seq.NEXTVAL,'tomate');
insert into ingredientes values (ingrediente_seq.NEXTVAL,'calabresa');
insert into ingredientes values (ingrediente_seq.NEXTVAL,'azeitona');


--Ingredientes - Petit gâteau de queijo
insert into ingredientes values (ingrediente_seq.NEXTVAL,'queijo cremoso');
insert into ingredientes values (ingrediente_seq.NEXTVAL,'cream cheese');
insert into ingredientes values (ingrediente_seq.NEXTVAL,'parmesao ralado');
insert into ingredientes values (ingrediente_seq.NEXTVAL,'manteiga');
insert into ingredientes values (ingrediente_seq.NEXTVAL,'gemas');

--medidas
--insert into medidas values (medida_seq.NEXTVAL,'NOME MEDIDA','ABREVIACAO');
insert into medidas values (medida_seq.NEXTVAL,'litro','L');
insert into medidas values (medida_seq.NEXTVAL,'gramas','g');
insert into medidas values (medida_seq.NEXTVAL,'dentes','dentes');
insert into medidas values (medida_seq.NEXTVAL,'fio','fio');
insert into medidas values (medida_seq.NEXTVAL,'a gosto','a gosto');
insert into medidas values (medida_seq.NEXTVAL,'unidade','uni');
insert into medidas values (medida_seq.NEXTVAL,'xicara (cha)','xic');
insert into medidas values (medida_seq.NEXTVAL,'colher (sopa)','csp');
insert into medidas values (medida_seq.NEXTVAL,'copo','cp');
insert into medidas values (medida_seq.NEXTVAL,'fatias','fatias');
insert into medidas values (medida_seq.NEXTVAL,'colheres (sobremesa)','csb');
insert into medidas values (medida_seq.NEXTVAL,'mililitro','ml');

--utensilios
--insert into utensilios values (utensilio_seq.NEXTVAL, 'NOME');
insert into utensilios values (utensilio_seq.NEXTVAL, 'Abridor de garrafa');
insert into utensilios values (utensilio_seq.NEXTVAL, 'Saca-rolhas');
insert into utensilios values (utensilio_seq.NEXTVAL, 'Assadeiras aluminio');
insert into utensilios values (utensilio_seq.NEXTVAL, 'Socador de alho');
insert into utensilios values (utensilio_seq.NEXTVAL, 'Funil');
insert into utensilios values (utensilio_seq.NEXTVAL, 'Escorredor de macarrao');
insert into utensilios values (utensilio_seq.NEXTVAL, 'Pa para bolo');
insert into utensilios values (utensilio_seq.NEXTVAL, 'Espatula de silicone');
insert into utensilios values (utensilio_seq.NEXTVAL, 'Espremedor de alho');
insert into utensilios values (utensilio_seq.NEXTVAL, 'Espremedor de batata');


--categorias
--insert into categorias values(categoria_seq.NEXTVAL, 'NOME CATEGORIA', SUPER CATEGRIA, SELECIONAVEL, 'SLUG');
--1
insert into categorias values(categoria_seq.NEXTVAL, 'Salgados', null, 0, 'Salgados');
--2
insert into categorias values(categoria_seq.NEXTVAL, 'Tortas', 1, 1, 'Tortas');
--3
insert into categorias values(categoria_seq.NEXTVAL, 'Petiscos', 1, 1, 'Petiscos');
--4
insert into categorias values(categoria_seq.NEXTVAL, 'Cotidiano', 1, 1, 'Cotidiano');
--5
insert into categorias values(categoria_seq.NEXTVAL, 'Gourmet', null, 0, 'Gourmet');
--6
insert into categorias values(categoria_seq.NEXTVAL, 'Petit gateau', 2, 1, 'Petit gateau');

--receitas
--insert into receitas values (receita_seq.NEXTVAL, 'NOME RECEITA', 2, 1, 2, 2, 'Modo de Preparo', null, 0, 0, 0, 'receita1', 1);


insert into receitas (id, nome, id_categoria, id_usuario, porcao, tempo_preparo, modo_preparo, img_path, pontuacao_media, views, favs, slug, aprovado) 
values (receita_seq.NEXTVAL, 'Arroz de Lentilha', 4, 1, 5, 20, 'Numa panela com água fervente em fogo médio coloque a lentilha, tempere com sal, tampe a panela e cozinhe por 20 minutos.<br/> Escorra a lentilha, reserve a água do cozimento e a lentilha.<br/> Depois de refogar bem o alho no azeite e o arroz, tempere com sal, coloque a água do cozimento da lentilha, tampe a panela e cozinhe por +/- 15 minutos.<br/> Depois misture o arroz cozido com a lentilha cozida e reserve.', null, 0, 0, 0, 'receita1', 1);

insert into receitas values (receita_seq.NEXTVAL, 'Cebola Frita', 3, 1, 3, 19, 'Com uma faca afiada corte as cebolas em fatias bem finas e seque bem com um papel absorvente.
Numa tigela com a farinha de trigo tempere com sal a gosto, passe as fatias de cebola nessa mistura, retire o excesso de farinha de trigo e frite as fatias em óleo quente até dourar.
Retire do óleo com a ajuda de uma escumadeira e coloque em papel absorvente.', null, 0, 0, 0, 'receita1', 1);

insert into receitas values (receita_seq.NEXTVAL, 'Torta a Portuguesa', 2, 1, 5, 160, 'Misture numa tigela ¼ xícara (chá) de azeite, ¼ xícara (chá) de pimentão verde bem picadinho, ¼ xícara (chá) de cebola picada, 1 colher (sopa) de pimenta dedo-de-moça sem semente picadinha, 2 ovos, 1 xícara (chá) de muçarela ralada no ralo grosso, ½ copo de iogurte natural e salsinha picadinha a gosto. Adicione 1 xícara (chá) de farinha de trigo, ½ colher (sopa) de sal, ½ colher (sopa) de fermento em pó e mexa bem.
Espalhe a massa numa forma para pizza redonda (30 cm de diâmetro) untada com manteiga e enfarinhada. Leve para assar em forno pré-aquecido a 200° C por 15 minutos.
Retire do forno e sobre a massa assada distribua muçarela ralada no ralo grosso, rodelas de tomate, fatias de ovo cozido, linguiça calabresa refogada e fatiada e azeitona verde fatiada a gosto. Regue o azeite e leve ao forno a 200° C por 15 minutos ou até derreter o queijo. Retire do forno e sirva em seguida.', null, 0, 0, 0, 'receita1', 1);

insert into receitas values (receita_seq.NEXTVAL, 'Petit gateau de queijo', 6, 1, 1, 80, 'Misture o queijo com o cream cheese, o parmesão, a manteiga, os ovos e as gemas e leve ao banho-maria, mexendo sempre, até ficar homogêneo.
Fora do fogo, acrescente a farinha e sal a gosto e misture bem até obter um creme. Cubra com filme plástico e leve à geladeira por no mínimo 1 hora.
Distribua em forminhas de petit gâteau ou de empada untadas com manteiga, sem encher totalmente, e leve ao forno quente preaquecido (250°C) por 7 a 10 minutos ou até a borda ficar firme e o centro, molinho. 
Desenforme e sirva em seguida.', null, 0, 0, 0, 'receita1', 1);

--receitasIngredientes

--arroz
insert into receitas_ingredientes values(receita_ingrediente_seq.NEXTVAL, 1, 1, 1, null, 1);
insert into receitas_ingredientes values(receita_ingrediente_seq.NEXTVAL, 1, 2, 2, null, 250);
insert into receitas_ingredientes values(receita_ingrediente_seq.NEXTVAL, 1, 3, 3, null, 2);
insert into receitas_ingredientes values(receita_ingrediente_seq.NEXTVAL, 1, 4, 4, null, 1);
insert into receitas_ingredientes values(receita_ingrediente_seq.NEXTVAL, 1, 5, 2, null, 250);
insert into receitas_ingredientes values(receita_ingrediente_seq.NEXTVAL, 1, 6, 5, null, 0);

--cebola
insert into receitas_ingredientes values(receita_ingrediente_seq.NEXTVAL, 2, 7, 6, null, 2);
insert into receitas_ingredientes values(receita_ingrediente_seq.NEXTVAL, 2, 8, 2, null, 500);
insert into receitas_ingredientes values(receita_ingrediente_seq.NEXTVAL, 2, 6, 5, null, 0);
insert into receitas_ingredientes values(receita_ingrediente_seq.NEXTVAL, 2, 9, 12, null, 500);

--torta
insert into receitas_ingredientes values(receita_ingrediente_seq.NEXTVAL, 3, 4, 12, null, 60);
insert into receitas_ingredientes values(receita_ingrediente_seq.NEXTVAL, 3, 10, 2, null, 40);
insert into receitas_ingredientes values(receita_ingrediente_seq.NEXTVAL, 3, 7, 2, null, 40);
insert into receitas_ingredientes values(receita_ingrediente_seq.NEXTVAL, 3, 11, 8, null, 1);
insert into receitas_ingredientes values(receita_ingrediente_seq.NEXTVAL, 3, 12, 6, null, 2);
insert into receitas_ingredientes values(receita_ingrediente_seq.NEXTVAL, 3, 13, 2, null, 100);
insert into receitas_ingredientes values(receita_ingrediente_seq.NEXTVAL, 3, 14, 2, null, 85);
insert into receitas_ingredientes values(receita_ingrediente_seq.NEXTVAL, 3, 15, 5, null, 0);
insert into receitas_ingredientes values(receita_ingrediente_seq.NEXTVAL, 3, 8, 2, null, 150);
insert into receitas_ingredientes values(receita_ingrediente_seq.NEXTVAL, 3, 6, 8, null, 1/2);
insert into receitas_ingredientes values(receita_ingrediente_seq.NEXTVAL, 3, 16, 8, null, 1/2);
insert into receitas_ingredientes values(receita_ingrediente_seq.NEXTVAL, 3, 13, 5, null, 0);
insert into receitas_ingredientes values(receita_ingrediente_seq.NEXTVAL, 3, 17, 5, null, 0);
insert into receitas_ingredientes values(receita_ingrediente_seq.NEXTVAL, 3, 12, 5, null, 0);
insert into receitas_ingredientes values(receita_ingrediente_seq.NEXTVAL, 3, 18, 5, null, 0);
insert into receitas_ingredientes values(receita_ingrediente_seq.NEXTVAL, 3, 19, 5, null, 0);
insert into receitas_ingredientes values(receita_ingrediente_seq.NEXTVAL, 3, 4, 5, null, 0);

--petit
insert into receitas_ingredientes values(receita_ingrediente_seq.NEXTVAL, 4, 20, 8, null, 5);
insert into receitas_ingredientes values(receita_ingrediente_seq.NEXTVAL, 4, 21, 11, null, 4);
insert into receitas_ingredientes values(receita_ingrediente_seq.NEXTVAL, 4, 22, 8, null, 4);
insert into receitas_ingredientes values(receita_ingrediente_seq.NEXTVAL, 4, 23, 8, null, 5);
insert into receitas_ingredientes values(receita_ingrediente_seq.NEXTVAL, 4, 12, 6, null, 2);
insert into receitas_ingredientes values(receita_ingrediente_seq.NEXTVAL, 4, 24, 6, null, 2);
insert into receitas_ingredientes values(receita_ingrediente_seq.NEXTVAL, 4, 8, 8, null, 2);
insert into receitas_ingredientes values(receita_ingrediente_seq.NEXTVAL, 4, 6, 5, null, 0);

commit;
