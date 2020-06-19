--\import lily.account
--\import lily.geo.zone
--\import lily.contact.partycat

    create sequence org_seq start with 1000;
    create table org(
        id          int primary key default nextval('org_seq'),
--\mixin lily.mixin.Acl_rw-r--r--
--\mixin lily.mixin.LabelExVer
--\mixin lily.mixin.Contact ""
--\mixin lily.mixin.Props

        cat         int
            references partycat on update cascade,

        birthday    date,
        locale      varchar(10),
        timezone    varchar(40),
        
    -- props:
        -- contacts: alternative contacts
        -- role.sameTrade?
        -- role.shipper?
        nrole       int not null default 0,
        supplier    boolean not null default false,
        customer    boolean not null default false,

        subject     varchar(200),

    -- props:
        -- bank[].id
        -- bank[].name
        -- bank[].account
        nbank       int not null default 0,

        size        int not null default 0,
        taxid       varchar(20) unique
    );

    create index org_label          on org(label);
    create index org_lastmod        on org(lastmod desc);
    create index org_priority       on org(priority);
    create index org_state          on org(state);
    create index org_uid_acl        on org(uid, acl);

