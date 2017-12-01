--\import violet.store.offstorel

    create or replace view v_offstorel as
        select
            a.*,
            art.label art_label,
            u.label "uid_label",
            g.label "gid_label"
        from offstorel a
            left join art on a.art = art.id
            left join "user" u on a.uid=u.id
            left join "group" g on a.gid=g.id;
