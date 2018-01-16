--\import violet.store.storeodr

    create or replace view v_storeodr as
        select a.*,
            prev.subject "prev_subject",
            form.label   "form_label",
            op.label     "op_label",
            org.label    "org_label",
            person.label "person_label",
            cat.label    "cat_label",
            ph.label     "phase_label"
        from storeodr a
            left join storeodr prev on a.prev = prev.id
            left join _form form    on a.form = form.id
            left join "user" op     on a.op = op.id
            left join org           on a.org = org.id
            left join person        on a.person = person.id
            left join salecat cat   on a.cat = cat.id
            left join salephase ph  on a.phase = ph.id;
