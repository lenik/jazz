
        subject     varchar(200) not null,
        op          int references "user"(id) on update cascade on delete set null,
        cat         int references _cat(id) on update cascade on delete set null,
        phase       int references _phase(id) on update cascade on delete set null,

--\include Text.sql
