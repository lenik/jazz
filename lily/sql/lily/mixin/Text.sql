
    -- Column-Group: Text
        text        text,
        form        int references _form(id) on update cascade on delete set null,
        formargs    text, -- used with the form.
