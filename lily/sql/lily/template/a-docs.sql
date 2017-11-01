--\import lily.inc.msg

    create sequence $1_doc_seq start with 1000;

    create table $1_doc(
        id          int primary key default nextval('$1_doc_seq'),

--\mixin lily.mixin.Acl_rw-r-----
--\mixin lily.mixin.Ex
--\mixin lily.mixin.Ver
--\mixin lily.mixin.Mi
--\mixin lily.mixin.Msg

        "$1"        ${2=int} not null
            references "$1"(id) on update cascade on delete cascade
    );

    create index $1_doc_lastmod         on $1_doc(lastmod desc);
    create index $1_doc_priority        on $1_doc(priority);
    create index $1_doc_state           on $1_doc(state);
    create index $1_doc_subject         on $1_doc(subject);
    create index $1_doc_uid_acl         on $1_doc(uid, acl);
    create index $1_doc_year            on $1_doc(year);
    create index $1_doc_t0t1            on $1_doc(t0, t1);
    create index $1_doc_t1              on $1_doc(t1);
