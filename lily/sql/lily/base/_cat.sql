--\import lily.base._schema

    create sequence _cat_seq start with 10000;

    create table _cat(
        id          int primary key default nextval('_cat_seq'),
--\mixin lily.mixin.Code
--\mixin lily.mixin.LabelExVer

        schema      int not null
            references _schema on update cascade on delete cascade,

        parent      int
            references _cat on update cascade on delete cascade,

        depth       int not null default -1,

--\mixin lily.mixin.Props
        -- default.bonus etc.

        -- @Redundant
        nobj        int not null default 0
    );

    create index _cat_label          on _cat(label);
    create index _cat_lastmod        on _cat(lastmod desc);
    create index _cat_priority       on _cat(priority);
    create index _cat_state          on _cat(state);
