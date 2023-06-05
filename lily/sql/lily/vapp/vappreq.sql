--\import lily.inc.msg
--\import lily.vapp.vappcat

    create sequence vappreq_seq start with 1000;
    create table vappreq(
        id          int primary key default nextval('vappreq_seq'),
--\mixin lily.mixin.Acl_rw-r--r--
--\mixin lily.mixin.ExVer
--\mixin lily.mixin.Code
--\mixin lily.mixin.Label
--\mixin lily.mixin.MiMsg
        dummy       int
    );

    create index vappreq_label            on vappreq(label);
    create index vappreq_subject          on vappreq(subject);
