--\import lily.account

    -- 课程分类
--\mixin lily.template.a-cat course

    -- 课程
    create sequence course_seq start with 1000;
    create table course(
        id          int primary key default nextval('course_seq'),
--\mixin lily.mixin.Acl_rw-r--r--
--\mixin lily.mixin.LabelExVer
--\mixin lily.mixin.PropFiles

        cat         int
            references coursecat(id) on update cascade,

--\mixin lily.mixin.FavLike

        -- 学分
        credit      int not null default 0,
        plugins     jsonb
    );

    create index course_lastmod         on course(lastmod desc);
    create index course_priority        on course(priority desc);
    create index course_state           on course(state);
    create index course_uid_acl         on course(uid, acl);

--\mixin lily.template.a-tag course
--\mixin lily.template.a-tags course int label
--\mixin lily.template.a-favs course int label
