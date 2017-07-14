--\import lily.base._formparm

    create or replace view _v_form as
        select *, array(
                select name || '=' || value
                from _formparm p
                where p.form = _form.id
            ) "parms"
        from _form;

