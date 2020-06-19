    create sequence $1tag_seq start with 1000;
    create table $1tag(
        id          int primary key default nextval('$1tag_seq'),
--\mixin lily.mixin.Acl_rw-r--r--
--\mixin lily.mixin.Code
--\mixin lily.mixin.LabelExVer

        parent      int
            references $1tag on update cascade on delete cascade,

        depth       int not null default -1,
        nref        int not null default 0 -- redundant.
    );

    create index $1tag_label        on $1tag(label);
    create index $1tag_lastmod      on $1tag(lastmod desc);
    create index $1tag_priority     on $1tag(priority);
    create index $1tag_state        on $1tag(state);
    create index $1tag_uid_acl      on $1tag(uid, acl);

