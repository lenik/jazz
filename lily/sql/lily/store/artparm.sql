--\import lily.account

    create sequence artparm_seq start with 1000;
    create table artparm(
        id          int primary key default nextval('artparm_seq'),
--\mixin lily.mixin.LabelExVer
        type        int
    );

    create index artparm_label          on artparm(label);
    create index artparm_lastmod        on artparm(lastmod desc);
    create index artparm_priority       on artparm(priority);
    create index artparm_state          on artparm(state);
