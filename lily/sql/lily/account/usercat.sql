
    create sequence usercat_seq start with 1000;
    create table usercat(
        id          int primary key default nextval('usercat_seq'),
        code        varchar(20) unique, -- not null?
--\mixin lily.mixin.LabelExVer
        
        image       varchar(200),
        imagealt    varchar(80)
    );

    create index usercat_label        on usercat(label);
    create index usercat_lastmod      on usercat(lastmod desc);
    create index usercat_priority     on usercat(priority);
    create index usercat_state        on usercat(state);

    insert into usercat(id, code, label, description)
        values(0, 'std', 'Default User Category', 'Default User Category');

