#\import lily.security

column-property {
    lng:                longitude
    lat:                latitude
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
