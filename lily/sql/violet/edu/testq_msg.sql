--\import lily.inc.msg
--\import violet.edu.testq

    -- listing, left message

    create sequence testq_msg_seq start with 1000;

    create table testq_msg(
        id          bigint primary key default nextval('testq_msg_seq'),
--\mixin lily.mixin.LabelExVer
--\mixin lily.mixin.MiMsg

        q           bigint not null
            references testq(id) on update cascade on delete cascade,

        parent      bigint
            references testq_msg(id) on update cascade on delete cascade
    );

    create index testq_msg_lastmod    on testq_msg(lastmod desc);
    
--\mixin lily.template.a-votes testq_msg bigint subject
