    create sequence uom_seq start with 1000;
    create table uom(
        id          int primary key default nextval('uom_seq'),
--\mixin lily.mixin.Code
--\mixin lily.mixin.Label

    -- props:
        -- qty.property (aka. uomprop)
        -- qty.digits
--\mixin lily.mixin.Props

        prop        varchar(30) not null default 'Measure',

        std         int
            references uom(id) on update cascade,
        scale       float not null default 0
    );

    create index uom_label       on uom(label);
