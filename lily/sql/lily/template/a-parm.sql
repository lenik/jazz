    create sequence $1parm_seq start with 1000;
    create table $1parm(
        id          int primary key default nextval('$1parm_seq'),
--\mixin lily.mixin.Acl_rw-r--r--
--\mixin lily.mixin.UniqName
--\mixin lily.mixin.LabelExVer

        type        varchar(30) not null default 'int',
        
        optional    boolean not null default false,
        
        -- default UOM
        uom         int
            references uom on update cascade,
            
        values      varchar(300)
    );

    create index $1parm_label       on $1parm(label);
    create index $1parm_lastmod     on $1parm(lastmod desc);
    create index $1parm_priority    on $1parm(priority);
    create index $1parm_state       on $1parm(state);
    create index $1parm_uid_acl     on $1parm(uid, acl);
