--\import violet.store.storel

    create or replace view v_storel as
        select
            a.*,
            art.label art_label,
            region.label region_label,
            region.path region_path,
            u.label "uid_label",
            g.label "gid_label"
        from storel a
            left join art on a.art = art.id
            left join region on a.region = region.id
            left join "user" u on a.uid=u.id
            left join "group" g on a.gid=g.id;
