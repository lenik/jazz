
        subject     varchar(200) not null,
        op          int references "user"(id) on update cascade on delete set null,
        _cat        int references cat(id) on update cascade on delete set null,
        _phase      int references phase(id) on update cascade on delete set null,

--\include Text.sql
