--\import lily.account.user

    create sequence userlog_seq start with 1000;
    create table userlog(
        id          int primary key default nextval('userlog_seq'),
--\mixin lily.mixin.LabelExVer
        ip          inet not null
    );

    create index userlog_label      on userlog(label);
    create index userlog_lastmod    on userlog(lastmod desc);
    create index userlog_state      on userlog(state);

    comment on table userlog is 'User login activity';
    comment on column userlog.ip is 'The source IP of the login';
