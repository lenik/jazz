--\import lily.inc.msg
--\import lily.pub.article

    -- listing, left message

    create sequence article_msg_seq start with 1000;

    create table article_msg(
        id          bigint primary key default nextval('article_msg_seq'),
--\mixin lily.mixin.ExVer
--\mixin lily.mixin.Mi
--\mixin lily.mixin.Msg

        article        bigint not null
            references article on update cascade on delete cascade,

        parent      bigint
            references article_msg on update cascade on delete cascade
    );

    create index article_msg_lastmod    on article_msg(lastmod desc);
    
--\mixin lily.template.a-votes article_msg bigint subject
