#\import lily.security

class-map {
    net.bodz.lily.schema.util.Uom: \
        uom
}

table-name {
    uom:                net.bodz.lily.schema.util.UomRow
}

table uom {
    column std {
        javaName: standard
    }
    column prop {
        javaName: property
    }
}