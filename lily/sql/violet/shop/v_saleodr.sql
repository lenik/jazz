-- import violet.shop.saleodr

    create or replace view v_saleodr as
        select a.*,
            prev.subject "prev_subject",
            form.label   "form_label",
            plan.subject "plan_subject",
            op.label     "op_label",
            org.label    "org_label",
            person.label "person_label",
            cat.label    "cat_label",
            phase.label  "phase_label"
        from saleodr a
            left join saleodr prev on a.prev=prev.id
            left join _form form on a.form=form.id
            left join plan on a.plan=plan.id
            left join "user" op on a.op=op.id
            left join org on a.org=org.id
            left join person on a.person=person.id
            left join salecat cat on a.cat=cat.id
            left join salephase phase on a.phase=phase.id;
