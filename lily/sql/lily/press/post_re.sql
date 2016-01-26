--\import lily.inc.msg
--\import lily.press.post

    create sequence post_re_seq start with 1000;

    create table post_re(
        id          int primary key default nextval('post_re_seq'),
--\mixin lily.mixin.LabelExVer
--\mixin lily.mixin.MiMsg

        post        int not null
            references post(id) on update cascade on delete cascade,

        parent      int
            references post_re(id) on update cascade on delete cascade
    );

    create index post_re_lastmod    on post_re(lastmod desc);
