--\import lily.base.att

    create sequence attval_seq;
    create table attval(
        id          int primary key default nextval('attval_seq'),
--\mixin lily.mixin.Code
--\mixin lily.mixin.LabelExVer

        att         int not null
            references att(id) on update cascade on delete cascade,

        val         text not null
    );

    create index attval_label       on attval(label);
    create index attval_priority    on attval(priority);
