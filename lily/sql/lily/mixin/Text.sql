
        text        text,
        form        int references _form(id) on update cascade on delete set null,
        args        text, -- used with the form.
