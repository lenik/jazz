--\import lily.base
--\import lily.account
--\import violet.edu.course

    create sequence testq_seq start with 1000;

    create table testq(
        id          bigint primary key default nextval('testq_seq'),
--\mixin lily.mixin.Acl_rw-r--r--
--\mixin lily.mixin.ExVer
--\mixin lily.mixin.Msg

        course  int not null
            references course(id) on update cascade,
        
--\mixin lily.mixin.Props
--\mixin lily.mixin.FavLike

        -- insert position of the answer. num of chars to the end if negative.
        -- for HTML or RichText, the index of the verbatim code is used.
        pos     int not null default -1,
        
        answer varchar(100) not null default ''
    );

    create index testq_lastmod        on testq(lastmod desc);
    create index testq_priority_nvote on testq(priority, nvote desc);
    create index testq_state          on testq(state);
    create index testq_subject        on testq(subject);
    create index testq_uid_acl        on testq(uid, acl);

--\mixin lily.template.a-tag testq
--\mixin lily.template.a-tags testq bigint subject
--\mixin lily.template.a-favs testq bigint subject
--\mixin lily.template.a-votes testq bigint subject
