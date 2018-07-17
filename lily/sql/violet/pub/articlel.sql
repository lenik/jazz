--\import lily.inc.msg
--\import violet.article.article

    create sequence articlel_seq start with 1000;

    create table articlel(
        id          bigint primary key default nextval('articlel_seq'),
--\mixin lily.mixin.LabelExVer
--\mixin lily.mixin.MiMsg

        article        bigint not null
            references article(id) on update cascade on delete cascade,

        parent      bigint
            references articlel(id) on update cascade on delete cascade
    );

    create index articlel_lastmod    on articlel(lastmod desc);
    
--\mixin lily.template.a-votes articlel bigint subject
