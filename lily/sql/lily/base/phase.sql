--\import lily.base.schema

    create sequence phase_seq start with 10000;

    create table phase(
        id          int primary key default nextval('phase_seq'),
--\mixin lily.mixin.Code
--\mixin lily.mixin.LabelExVer
        schema      int not null
            references schema(id) on update cascade on delete cascade
    );

    create index phase_label        on phase(label);
    create index phase_lastmod      on phase(lastmod desc);
    create index phase_priority     on phase(priority);
    create index phase_state        on phase(state);
