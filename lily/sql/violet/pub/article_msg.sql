--\import lily.inc.msg
--\import violet.pub.article

    -- listing, left message

    create sequence article_msg_seq start with 1000;

    create table article_msg(
        id          bigint primary key default nextval('article_msg_seq'),
--\mixin lily.mixin.LabelExVer
--\mixin lily.mixin.MiMsg

        article        bigint not null
            references article(id) on update cascade on delete cascade,

        parent      bigint
            references article_msg(id) on update cascade on delete cascade
    );

    create index article_msg_lastmod    on articlel(lastmod desc);
    
--\mixin lily.template.a-votes article_msg bigint subject
