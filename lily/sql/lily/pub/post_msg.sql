--\import lily.inc.msg
--\import lily.pub.post

    create sequence post_msg_seq start with 1000;

    create table post_msg(
        id          bigint primary key default nextval('post_msg_seq'),
--\mixin lily.mixin.ExVer
--\mixin lily.mixin.MiMsg

        post        bigint not null
            references post on update cascade on delete cascade,

        parent      bigint
            references post_msg on update cascade on delete cascade
    );

    create index post_msg_lastmod    on post_msg(lastmod desc);
        
--\mixin lily.template.a-votes post_msg bigint subject
