--\import lily.contact.org
--\import lily.contact.orgunit
--\import lily.contact.person

    create sequence personrole_seq;
    create table personrole(
        id          int primary key default nextval('personrole_seq'),
        org         int not null
            references org on update cascade on delete cascade,

        ou          int
            references orgunit on update cascade on delete cascade,

        person      int not null
            references person on update cascade on delete cascade,

        role        varchar(20),
        description varchar(200),

        constraint personrole_uk unique(org, ou, person)
    );

