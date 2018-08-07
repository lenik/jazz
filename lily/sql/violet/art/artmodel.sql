--\import lily.account

    create sequence artmodel_seq start with 1000;
    create table artmodel(
        id          int primary key default nextval('artmodel_seq'),
--\mixin lily.mixin.Acl_rw-r--r--
--\mixin lily.mixin.LabelExVer
        -- state:
            -- VALID := valid
            -- INVALID := not valid
            -- OBSOLETE := obsolete is not null
            -- EXPIRED := not between validsince and validuntil
--\mixin lily.mixin.Mi
--\mixin lily.mixin.Props
        -- price-strategy
        -- cost: { labor, elec, equip, other }
--\mixin lily.mixin.ValidControl

        obsolete    int
            references artmodel(id) on update cascade on delete set null,

        art         int not null
            references art(id) on update cascade on delete cascade,
        -- model name, or model version
        model       varchar(40),
        
        unique(art, model) -- with (fillfactor = 70)
    );

    create index artmodel_label       on artmodel(label);
    create index artmodel_lastmod     on artmodel(lastmod desc);
    create index artmodel_priority    on artmodel(priority);
    create index artmodel_state       on artmodel(state);
    create index artmodel_uid_acl     on artmodel(uid, acl);
