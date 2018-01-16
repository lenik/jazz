--\import violet.art.artcat

    create or replace view v_artcat_n as select
        (select count(*) from artcat) total,
        (select count(distinct cat) from art) used;

    create or replace view v_artcat_hist as
        select a.*, c.* -- c.label c_label
        from (select cat, count(*) n,
                max(lastmod) lastmod_max from art group by cat) a
            left join artcat c on a.cat=c.id
        order by priority, n desc;
