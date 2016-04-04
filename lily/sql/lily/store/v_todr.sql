--\import lily.store.todr

    create or replace view v_todr as
        select a.*,
            prev.subject "prev_subject",
            _form.label  "form_label",
            op.label     "op_label",
            org.label    "org_label",
            person.label "person_label",
            _cat.label   "cat_label",
            _phase.label "phase_label"
        from todr a
            left join todr prev on a.prev = prev.id
            left join _form     on a.form = _form.id
            left join "user" op on a.op = op.id
            left join org       on a.org = org.id
            left join person    on a.person = person.id
            left join _cat      on a.cat = _cat.id
            left join _phase    on a.phase = _phase.id;

