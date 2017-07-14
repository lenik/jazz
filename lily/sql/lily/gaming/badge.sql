--\import lily.base._schema

    create sequence badge_seq start with 10000;

    create table badge(
        id          int primary key default nextval('badge_seq'),
--\mixin lily.mixin.LabelExVer

        expr        varchar(255),
        val         int not null,
        levels      int[],

        descend     boolean not null default false,
        transient   boolean not null default false,
        indexed     boolean not null default false,

        image       varchar(30)
    );

    create index badge_label          on badge(label);
    create index badge_lastmod        on badge(lastmod desc);
    create index badge_priority       on badge(priority);
    create index badge_version        on badge(state);
    create index badge_expr           on badge(expr);
    --create index badge_indexed        on badge(state);
