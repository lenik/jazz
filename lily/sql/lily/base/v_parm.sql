--\import lily.base.parm

    create or replace view v_parm as
        select *, array(select val from parmval a where a.parm = parm.id) "vals" from parm;

