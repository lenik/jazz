--\import lily.base._parm

    create or replace view _v_parm as
        select *, array(
                select val
                from _parmval pv
                where pv.parm = _parm.id
            ) "vals"
        from _parm;

