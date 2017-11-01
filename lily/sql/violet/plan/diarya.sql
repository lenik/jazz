--\import lily.inc.msg
--\import violet.plan.diary

    create sequence diarya_seq start with 1000;

    create table diarya(
        id          bigint primary key default nextval('diarya_seq'),
--\mixin lily.mixin.LabelExVer
--\mixin lily.mixin.MiMsg

        diary       bigint not null
            references diary(id) on update cascade on delete cascade,

        parent      bigint
            references diarya(id) on update cascade on delete cascade
    );

    create index diarya_lastmod    on diarya(lastmod desc);
        
--\mixin lily.template.a-votes diarya bigint subject
