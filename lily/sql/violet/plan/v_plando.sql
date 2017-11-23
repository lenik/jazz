--\import violet.plan.plando

    create or replace view v_plando as
        select *,
            array(select tag || ':' || tag.label
                from plando_tag a left join plandotag tag on a.tag=tag.id where a.plando=plando.id) tags,
            array(select parm || ':' || parm.label || '=' || a.ival
                from plando_parm a left join plandoparm parm on a.parm=parm.id where a.plando=plando.id) parms
        from plando;

