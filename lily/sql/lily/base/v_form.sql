--\import lily.base.formcp

    create or replace view v_form as
        select *, 
            array(select name || '=' || value from formcp p where p.form = form.id) "params"
        from form;
