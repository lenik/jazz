--\import lily.inc.msg
--\import violet.post.post

    create sequence posta_seq start with 1000;

    create table posta(
        id          bigint primary key default nextval('posta_seq'),
--\mixin lily.mixin.LabelExVer
--\mixin lily.mixin.MiMsg

        post        bigint not null
            references post(id) on update cascade on delete cascade,

        parent      bigint
            references posta(id) on update cascade on delete cascade
    );

    create index posta_lastmod    on posta(lastmod desc);
        
--\mixin lily.template.a-votes posta bigint subject
