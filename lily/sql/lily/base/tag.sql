--\import lily.base.tagv

    create sequence tag_seq start with 10000;

    create table tag(
        id          int primary key default nextval('tag_seq'),
--\mixin lily.mixin.LabelExVer
        tagv        int not null
            references tagv(id) on update cascade on delete cascade
    );

    create index tag_label          on tag(label);
    create index tag_lastmod        on tag(lastmod desc);
    create index tag_priority       on tag(priority);
    create index tag_state          on tag(state);
