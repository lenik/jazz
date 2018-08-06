--\import lily.inc.msg
--\import violet.plan.diary

    create sequence diaryrev_seq start with 1000;

    create table diaryrev(
        id          bigint primary key default nextval('diaryrev_seq'),
--\mixin lily.mixin.ExVer
--\mixin lily.mixin.MiMsg

        diary       bigint not null
            references diary(id) on update cascade on delete cascade,

        parent      bigint
            references diaryrev(id) on update cascade on delete cascade
    );

    create index diaryrev_lastmod    on diaryrev(lastmod desc);
        
--\mixin lily.template.a-votes diaryrev bigint subject
