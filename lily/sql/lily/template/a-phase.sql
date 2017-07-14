    create sequence $1phase_seq start with 1000;
    create table $1phase(
        id          int primary key default nextval('$1phase_seq'),
--\mixin lily.mixin.Acl_rw-r--r--
--\mixin lily.mixin.Code
--\mixin lily.mixin.Label
--\mixin lily.mixin.Ex

        nref        int not null default 0 -- redundant.
    );

    create index $1phase_label       on $1phase(label);
    create index $1phase_priority    on $1phase(priority);
    create index $1phase_uid_acl     on $1phase(uid, acl);

