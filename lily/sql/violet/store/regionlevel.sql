
    create sequence regionlevel_seq start with 1000;
    create table regionlevel(
        id          int primary key default nextval('regionlevel_seq'),
--\mixin lily.mixin.LabelExVer
--\mixin lily.mixin.Props
        dummy       int
    );

    create index regionlevel_lastmod         on regionlevel(lastmod desc);
    create index regionlevel_priority        on regionlevel(priority desc);
    create index regionlevel_state           on regionlevel(state);
    
    insert into regionlevel(id, label) values(0, 'abstract');
    insert into regionlevel(id, label) values(1, 'place');
    insert into regionlevel(id, label) values(2, 'room');
    insert into regionlevel(id, label) values(3, 'shelf');

