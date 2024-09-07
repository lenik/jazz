#\import lily.security

column-property {
    odr:            orderId
}

class-map {
    net.bodz.lily.concrete.CoCategory: \
        regioncat, \
        storecat
    net.bodz.lily.concrete.CoCode: \
        regionlevel
    net.bodz.lily.concrete.CoImagedEvent: \
        storeodrl
    net.bodz.lily.concrete.CoNode: \
        region
    net.bodz.lily.concrete.CoPhase: \
        storephase
    net.bodz.lily.concrete.CoTag: \
        regiontag
}

table-name {
    region:             net.bodz.violet.schema.store.Region
    regioncat:          net.bodz.violet.schema.store.RegionCategory
    regiontag:          net.bodz.violet.schema.store.RegionTag
    regionlevel:        net.bodz.violet.schema.store.RegionLevel

    storecat:           net.bodz.violet.schema.store.StoreCategory
    storephase:         net.bodz.violet.schema.store.StorePhase
    storel:             net.bodz.violet.schema.store.StoreItem
    offstorel:          net.bodz.violet.schema.store.OffStoreItem

    storeodr:           net.bodz.violet.schema.store.StoreOrder
    storeodrl:          net.bodz.violet.schema.store.StoreOrderItem
}

table region {
    column x {
        javaName: position.dx
    }
    column y {
        javaName: position.dy
    }
    column z {
        javaName: position.dz
    }
    column dx {
        javaName: bbox.dx
    }
    column dy {
        javaName: bbox.dy
    }
    column dz {
        javaName: bbox.dz
    }
}

table storeodr {
    column ou {
        javaName: orgUnit
    }
}

table storeodrl {
}
