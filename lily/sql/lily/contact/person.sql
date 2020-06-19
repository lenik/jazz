--\import lily.account
--\import lily.contact.org
--\import lily.contact.orgunit
--\import lily.geo.zone
--\import lily.contact.partycat

    create sequence person_seq start with 1000;
    create table person(
        id          int primary key default nextval('person_seq'),
--\mixin lily.mixin.Acl_rw-r--r--
--\mixin lily.mixin.LabelExVer
--\mixin lily.mixin.Contact ""
--\mixin lily.mixin.Props

        cat         int
            references partycat on update cascade,

        birthday    date,
        locale      varchar(10) not null default 'zh-cn',
        timezone    varchar(40),

    -- props:
        -- contacts: alternative contacts
        -- role.sameTrade?
        -- role.shipper?
        nrole       int not null default 0,
        customer    boolean not null default false,
        supplier    boolean not null default false,
        employee    boolean not null default false,

        subject     varchar(200),

    -- props:
        -- bank[].id
        -- bank[].name
        -- bank[].account
        nbank       int not null default 0,

        gender      char,
        homeland    varchar(10),
        passport    varchar(20),

        ssn         varchar(20) unique,     -- social security number
        dln         varchar(20)             -- driver's license number
    );

    create index person_label       on person(label);
    create index person_lastmod     on person(lastmod desc);
    create index person_priority    on person(priority);
    create index person_state       on person(state);
    create index person_uid_acl     on person(uid, acl);

    alter table "user"
        add column person int
            references person on update cascade;

