--\import lily.account.user

    create sequence storage_seq start with 1000;
    create table "storage"(
        id          int primary key default nextval('storage_seq'),
--\mixin lily.mixin.LabelExVer
--\mixin lily.mixin.Props

        -- the preferred dirname for this storage.
        name        varchar(30) not null unique
    );

    create index storage_label         on "storage"(label);
    create index storage_lastmod       on "storage"(lastmod desc);
    create index storage_state         on "storage"(state);
  --create index storage_props         on "storage" using gin(props);
