--\import lily.account.group
--\import lily.account.user

    create or replace view v_group as
        select *, 
            array(select "user" from userof a where a."group"=g.id) users,
            array(select coalesce(label, code) from userof a left join "user" u on a."user"=u.id 
                where a."group"=g.id) labels
        from "group" g;

