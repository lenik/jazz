--\import violet.plan.plan

    create or replace view v_plan as
        select *,
            array(select tag || ':' || tag.label
                from plan_tag a left join plantag tag on a.tag=tag.id where a.plan=plan.id) tags,
            array(select parm || ':' || parm.label || '=' || a.ival
                from plan_parm a left join planparm parm on a.parm=parm.id where a.plan=plan.id) parms
        from plan;

