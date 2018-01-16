--\import violet.tran.tranodr
--\import violet.tran.tranodrl

    create or replace view v_tranodr as
        select a.*,
            prev.subject "prev_subject",
            saleodr.subject "sale_subject",
            storeodr.subject "store_subject",
            op.label     "op_label",
            org.label    "org_label",
            person.label "person_label",
            cat.label    "cat_label",
            phase.label  "phase_label"
        from tranodr a
            left join tranodr prev on a.prev=prev.id
            left join saleodr on a.saleodr=saleodr.id
            left join storeodr on a.storeodr=storeodr.id
            left join "user" op on a.op=op.id
            left join org on saleodr.cust_org=org.id
            left join person on saleodr.cust_person=person.id
            left join trancat cat on a.cat=cat.id
            left join tranphase phase on a.phase=phase.id;

    create or replace view v_tranodr_n as select
        (select count(*) from tranodr) "total",
        (select count(*) from tranodr where t1 is null) "nleft";
