#\import lily.security

column-property {
    lng:                longitude
    lat:                latitude
}

class-map {
    net.bodz.lily.concrete.CoCategory: \
        zonecat
    net.bodz.lily.concrete.CoImaged: \
        zone
}

table-name {
    zone:               net.bodz.lily.schema.geo.Zone
    zonecat:            net.bodz.lily.schema.geo.ZoneCategory
}

table zone {
    column telcode {
        javaName: telCode
    }
    column postcode {
        javaName: postCode
    }
}
