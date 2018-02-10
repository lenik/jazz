--\import lily.account.group
--\import lily.account.user

    create or replace view v_user as
        select u.*, g.label "label0",
            array(select "group" from group_user a where a."user"=u.id) groups,
            array(select coalesce(label, code) from group_user a left join "group" g on a."group"=g.id
                where a."user"=u.id) labels
        from "user" u left join "group" g on u.gid0 = g.id;
