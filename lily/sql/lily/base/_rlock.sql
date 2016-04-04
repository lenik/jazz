--\import lily.base._schema
--\import lily.base._cat
--\import lily.base._form
--\import lily.base._parm
--\import lily.base._phase
--\import lily.base._tagv

    create table _rlock(
        schema      int unique references _schema(id) on update cascade,
        cat         int unique references    _cat(id) on update cascade,
        form        int unique references   _form(id) on update cascade,
        parm        int unique references   _parm(id) on update cascade,
        phase       int unique references  _phase(id) on update cascade,
        tagv        int unique references   _tagv(id) on update cascade
    );
