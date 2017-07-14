
    create or replace view v_diary as
        select a.*,
            u.label "uid_label",
            g.label "gid_label",
            op.label "op_label",
            form.label "form_label",
            cat.label "cat_label",
            phase.label "phase_label"
        from diary a
            left join "user" u on a.uid=u.id
            left join "group" g on a.gid=g.id
            left join "user" op on a.op=op.id
            left join _form form on a.form=form.id
            left join diarycat cat on a.cat=cat.id
            left join diaryphase phase on a.phase=phase.id;
