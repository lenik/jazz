--\import lily.account
--\import lily.util.uom
--\import violet.art.artcat

    create sequence arttype_seq start with 1000;
    create table arttype(
        id          int primary key default nextval('arttype_seq'),

--\mixin lily.mixin.Acl_rw-r--r--
--\mixin lily.mixin.LabelExVer

        cat         int
            references artcat(id) on update cascade,

        parent      int
            references arttype(id) on update cascade,
            
        uom         int
            references uom(id) on update cascade
    );

    create index arttype_label          on arttype(label);
    create index arttype_lastmod        on arttype(lastmod desc);
    create index arttype_cat            on arttype(cat);
    create index arttype_parent         on arttype(parent);
