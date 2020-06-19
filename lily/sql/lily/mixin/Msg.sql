
    -- Column-Group: Msg
        subject     varchar(200) not null,
        op          int references "user" on update cascade on delete set null,
--\include Text.sql
