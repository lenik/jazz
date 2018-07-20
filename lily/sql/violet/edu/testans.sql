--\import lily.inc.msg
--\import violet.edu.testq

    create sequence testans_seq start with 1000;

    create table testans(
        id          bigint primary key default nextval('testans_seq'),
--\mixin lily.mixin.LabelExVer
--\mixin lily.mixin.Props

        q           bigint not null
            references testq(id) on update cascade on delete cascade,
        
        valid     boolean not null default true
    );

    create index testans_label       on testans(label);
    create index testans_lastmod    on testans(lastmod desc);
