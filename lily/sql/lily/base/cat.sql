--\import lily.base.schema

    create sequence cat_seq start with 10000;

    create table cat(
        id          int primary key default nextval('cat_seq'),
--\mixin lily.mixin.Code
--\mixin lily.mixin.LabelExVer

        schema      int not null
            references schema(id) on update cascade on delete cascade,

        parent      int
            references cat(id) on update cascade on delete cascade,

        depth       int not null default -1,

--\mixin lily.mixin.Props
        -- default.bonus etc.

        -- @Redundant
        nobj        int not null default 0
    );

    create index cat_label          on cat(label);
    create index cat_lastmod        on cat(lastmod desc);
    create index cat_priority       on cat(priority);
    create index cat_state          on cat(state);
