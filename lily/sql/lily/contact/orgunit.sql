--\import lily.contact.org
--\import lily.geo.zone

    create sequence orgunit_seq start with 1000;

    create table orgunit(
        id          int primary key default nextval('orgunit_seq'),
--\mixin lily.mixin.Acl_rw-r--r--
--\mixin lily.mixin.LabelExVer
--\mixin lily.mixin.Contact ""
--\mixin lily.mixin.Props

        org         int not null
            references org on update cascade on delete cascade,

        parent      int
            references orgunit on update cascade on delete cascade,

        depth       int not null default 0
    );

    create index orgunit_label      on orgunit(label);
    create index orgunit_lastmod    on orgunit(lastmod desc);
    create index orgunit_priority   on orgunit(priority);
    create index orgunit_state      on orgunit(state);
    create index orgunit_uid_acl    on orgunit(uid, acl);

