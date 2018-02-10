--\import lily.account.group
--\import lily.account.user

    create or replace view v_group as
        select *,
            array(select "user" from group_user a where a."group"="group".id) users,
            array(select coalesce(label, code) from group_user a left join "user" u on a."user"=u.id
                where a."group"="group".id) labels
        from "group";
