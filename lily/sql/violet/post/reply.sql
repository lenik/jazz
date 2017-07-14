--\import lily.inc.msg
--\import violet.post.post

    create sequence reply_seq start with 1000;

    create table reply(
        id          int primary key default nextval('reply_seq'),
--\mixin lily.mixin.LabelExVer
--\mixin lily.mixin.MiMsg

        post        int not null
            references post(id) on update cascade on delete cascade,

        parent      int
            references reply(id) on update cascade on delete cascade
    );

    create index reply_lastmod    on reply(lastmod desc);
