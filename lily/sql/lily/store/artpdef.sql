--\import lily.account

    create sequence artpdef_seq start with 1000;
    create table artpdef(
        id          int primary key default nextval('artpdef_seq'),
--\mixin lily.mixin.LabelExVer
        type        int
    );

    create index artpdef_label          on artpdef(label);
    create index artpdef_lastmod        on artpdef(lastmod desc);
    create index artpdef_priority       on artpdef(priority);
    create index artpdef_state          on artpdef(state);
