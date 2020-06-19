--\import lily.base._schema

    create sequence _phase_seq start with 10000;

    create table _phase(
        id          int primary key default nextval('_phase_seq'),
--\mixin lily.mixin.Code
--\mixin lily.mixin.LabelExVer
        schema      int not null
            references _schema on update cascade on delete cascade
    );

    create index _phase_label        on _phase(label);
    create index _phase_lastmod      on _phase(lastmod desc);
    create index _phase_priority     on _phase(priority);
    create index _phase_state        on _phase(state);
