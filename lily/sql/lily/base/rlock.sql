--\import lily.base.schema
--\import lily.base.cat
--\import lily.base.phase
--\import lily.base.parm
--\import lily.base.tagv
--\import lily.base.form

    create table rlock(
        schema      int unique references schema(id) on update cascade,
        cat         int unique references    cat(id) on update cascade,
        phase       int unique references  phase(id) on update cascade,
        parm       int unique references  parm(id) on update cascade,
        tagv        int unique references   tagv(id) on update cascade,
        form        int unique references   form(id) on update cascade
    );
