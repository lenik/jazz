--\import lily.base.schema
--\import lily.base.cat
--\import lily.base.phase
--\import lily.base.att
--\import lily.base.tagv
--\import lily.base.form

    create table rlock(
        schema      int unique references schema(id) on update cascade,
        cat         int unique references    cat(id) on update cascade,
        phase       int unique references  phase(id) on update cascade,
        att         int unique references    att(id) on update cascade,
        tagv        int unique references   tagv(id) on update cascade,
        form        int unique references   form(id) on update cascade
    );
