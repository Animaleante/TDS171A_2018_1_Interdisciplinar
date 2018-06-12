create or replace procedure pesquisarReceitaPorIngrediente(lista VARCHAR2)
as
    id receitas.id%TYPE;
    nome receitas.nome%TYPE;
    img_path receitas.img_path%TYPE;
    pontuacao_media receitas.pontuacao_media%TYPE;
    favs receitas.favs%TYPE;
begin
    select 
        distinct r.id, 
        r.nome, 
        r.img_path, 
        r.pontuacao_media, 
        r.favs 
    into
        id, 
        nome, 
        img_path, 
        pontuacao_media, 
        favs
    from 
        receitas r 
    inner join 
        receitas_ingredientes ri 
    on 
        r.id = ri.id_receita 
    where 
        (instr ('1,7', ri.id_ingrediente) > 0) 
    and 
        r.aprovado = 1
    and
        rownum <=1;
    dbms_output.put_line (id||' '||nome||''||img_path||''||pontuacao_media||''||favs);
end;
/


----------------
-- compila com erros
create or replace procedure pesquisarReceitaPorIngrediente(lista VARCHAR2)
is
    type xx is RECORD
    (
        id              receitas.id%TYPE,
        nome            receitas.nome%TYPE,
        img_path        receitas.img_path%TYPE,
        pontuacao_media receitas.pontuacao_media%TYPE,
        favs            receitas.favs%TYPE;
    );

    type var is table of xx index by pls_integer;

    var1 var;
begin
    select 
        distinct r.id, 
        r.nome, 
        r.img_path, 
        r.pontuacao_media, 
        r.favs 
    bulk
        collect into var1
    from 
        receitas r 
    inner join 
        receitas_ingredientes ri 
    on 
        r.id = ri.id_receita 
    where 
        (instr ('1,7', ri.id_ingrediente) > 0) 
    and 
        r.aprovado = 1
    and
        rownum <=1;

    for i in 1..var1.count
    loop
        dbms_output.put_line (
            var1 (i).id
            || var1 (i).nome
            || var1 (i).img_path
            || var1 (i).pontuacao_media
            || var1 (i).favs
        );
    end loop;
end;
/


create trigger receita_pontuacao_calculo 
after insert 
on pontuacoes
for each row
declare
    Pragma Autonomous_Transaction;
    soma number(11);
    linhas number(11);
    average number(5,2);
begin
    select sum(qty)+:new.qty into soma from pontuacoes where id_receita = :new.id_receita;
    select count(id_receita)+1 into linhas from pontuacoes where id_receita = :new.id_receita;
    select soma/linhas into average from dual;
    dbms_output.put_line(average);
    update receitas set pontuacao_media = average where id = :new.id_receita;
    commit;
end;
/
