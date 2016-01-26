--\import lily.store.todr

    create or replace view v_todr as
        select a.*,
            prev.subject "prev_subject",
            form.label   "form_label",
            op.label     "op_label",
            org.label    "org_label",
            person.label "person_label",
            cat.label    "cat_label",
            phase.label  "phase_label"
        from todr a
            left join todr prev on a.prev=prev.id
            left join form on a.form=form.id
            left join "user" op on a.op=op.id
            left join org on a.org=org.id
            left join person on a.person=person.id
            left join cat on a.cat=cat.id
            left join phase on a.phase=phase.id;

