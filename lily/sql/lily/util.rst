#\import lily.security

class-map {
    net.bodz.lily.concrete.CoCode: \
        uom
}

table-name {
    uom:                net.bodz.lily.schema.util.Uom
}

table uom {
    column std {
        javaName: standard
    }
    column prop {
        javaName: property
    }
}