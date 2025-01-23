
    create sequence regionlevel_seq start with 1000;
    create table regionlevel(
        id          int primary key default nextval('regionlevel_seq'),
--\mixin lily.mixin.LabelExVer
--\mixin lily.mixin.PropFiles
        dummy       int
    );

    create index regionlevel_lastmod         on regionlevel(lastmod desc);
    create index regionlevel_priority        on regionlevel(priority desc);
    create index regionlevel_state           on regionlevel(state);
    
    insert into regionlevel(id, label) values
        (0, 'abstract'),
        (1, 'place'),
        (2, 'room'),
        (3, 'shelf');
