--\import violet.ship.shipodr

    create or replace view v_shipodr as
        select a.*,
            prev.subject "prev_subject",
            saleodr.subject "sale_subject",
            op.label     "op_label",
            org.label    "org_label",
            person.label "person_label",
            cat.label    "cat_label",
            phase.label  "phase_label"
        from shipodr a
            left join shipodr prev on a.prev=prev.id
            left join saleodr on a.saleodr=saleodr.id
            left join "user" op on a.op=op.id
            left join org on saleodr.org=org.id
            left join person on saleodr.person=person.id
            left join shipcat cat on a.cat=cat.id
            left join shipphase phase on a.phase=phase.id;

    create or replace view v_shipodr_n as select
        (select count(*) from shipodr) "total",
        (select count(*) from shipodr where t1 is null) "shipping";
