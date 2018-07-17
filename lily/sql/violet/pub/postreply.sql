--\import lily.inc.msg
--\import violet.pub.post

    create sequence postreply_seq start with 1000;

    create table postreply(
        id          bigint primary key default nextval('postreply_seq'),
--\mixin lily.mixin.LabelExVer
--\mixin lily.mixin.MiMsg

        post        bigint not null
            references post(id) on update cascade on delete cascade,

        parent      bigint
            references postreply(id) on update cascade on delete cascade
    );

    create index postreply_lastmod    on postreply(lastmod desc);
        
--\mixin lily.template.a-votes postreply bigint subject
