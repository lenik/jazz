--\import violet.issue.issue

    create or replace view v_issuel as
        select *,
            array(select tag || ':' || tag.label
                from issue_tag a
                left join issuetag tag on a.tag=tag.id
                where a.issue=issue.id) tags,
            array(select parm || ':' || parm.label || '=' || a.ival
                from issue_parm a
                left join issueparm parm on a.parm=parm.id
                where a.issue=issue.id) parms
        from issuel l left join issue on l.issue = issue.id;
