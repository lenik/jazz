--\import lily.base._tagv

    create sequence _tag_seq start with 10000;

    create table _tag(
        id          int primary key default nextval('_tag_seq'),
--\mixin lily.mixin.LabelExVer
        tagv        int not null
            references _tagv on update cascade on delete cascade
    );

    create index _tag_label          on _tag(label);
    create index _tag_lastmod        on _tag(lastmod desc);
    create index _tag_priority       on _tag(priority);
    create index _tag_state          on _tag(state);
