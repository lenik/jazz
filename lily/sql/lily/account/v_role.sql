--\import lily.account.role
--\import lily.account.user

    create or replace view v_role as
        select *,
            array(select "user" from role_user a where a."role"="role".id) users,
            array(select coalesce(label, code) from role_user a left join "user" u on a."user"=u.id
                where a."role"="role".id) labels
        from "role";
