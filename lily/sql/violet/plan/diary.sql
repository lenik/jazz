--\import lily.contact.org
--\import lily.contact.person
--\mixin lily.template.a-cat diary
--\mixin lily.template.a-phase diary

    create sequence diary_seq start with 1000;
    create table diary(
        id          bigint primary key default nextval('diary_seq'),

--\mixin lily.mixin.Acl_rw-r-----
--\mixin lily.mixin.Ex
--\mixin lily.mixin.Mi
--\mixin lily.mixin.Msg
--\mixin lily.mixin.Ver

        cat         int not null
            references diarycat(id) on update cascade,

        phase       int not null
            references diaryphase(id) on update cascade,

        value       int not null default 0
    );

    create index diary_lastmod        on diary(lastmod desc);
    create index diary_priority       on diary(priority);
    create index diary_state          on diary(state);
    create index diary_subject        on diary(subject);
    create index diary_t0t1           on diary(t0, t1);
    create index diary_t1             on diary(t1);
    create index diary_uid_acl        on diary(uid, acl);

    create sequence diary_party_seq;
    create table diary_party(
        id          bigint primary key default nextval('diary_party_seq'),

        diary       bigint not null
            references diary(id) on update cascade on delete set null,

        person      int
            references person(id) on update cascade on delete set null,

        org         int
            references org(id)  on update cascade on delete set null,

        description varchar(60)
    );

--\mixin lily.template.a-tag diary
--\mixin lily.template.a-tags diary bigint subject
--\mixin lily.template.a-parm diary
--\mixin lily.template.a-parms diary bigint subject
--\mixin lily.template.a-votes diary bigint subject
