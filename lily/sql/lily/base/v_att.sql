--\import lily.base.att

    create or replace view v_att as
        select *, array(select val from attval a where a.att = att.id) "vals" from att;

