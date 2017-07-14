--\import violet.plan.plog

    create or replace view v_plog as
        select *,
            array(select tag || ':' || tag.label
                from plog_tag a left join plogtag tag on a.tag=tag.id where a.plog=plog.id) tags,
            array(select parm || ':' || parm.label || '=' || a.ival
                from plog_parm a left join plogparm parm on a.parm=parm.id where a.plog=plog.id) parms
        from plog;

