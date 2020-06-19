--\import lily.base._schema
--\import lily.base._cat
--\import lily.base._form
--\import lily.base._parm
--\import lily.base._phase
--\import lily.base._tagv

    create table _rlock(
        schema      int unique references _schema on update cascade,
        cat         int unique references    _cat on update cascade,
        form        int unique references   _form on update cascade,
        parm        int unique references   _parm on update cascade,
        phase       int unique references  _phase on update cascade,
        tagv        int unique references   _tagv on update cascade
    );
