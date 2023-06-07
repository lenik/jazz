#\import lily.security

column-property {
}

class-map {
    net.bodz.lily.template.CoCategory: \
        regioncat, \
        storecat
    net.bodz.lily.model.base.CoNode: \
        region
    net.bodz.lily.model.base.CoCode: \
        regionlevel
    net.bodz.lily.template.CoTag: \
        regiontag
    net.bodz.lily.template.CoPhase: \
        storephase
    net.bodz.lily.model.base.CoMomentInterval: \
        storeodrl
}

table-name {
    region:             net.bodz.violet.store.Region
    regioncat:          net.bodz.violet.store.RegionCategory
    regiontag:          net.bodz.violet.store.RegionTag
    regionlevel:        net.bodz.violet.store.RegionLevel

    storecat:           net.bodz.violet.store.StoreCategory
    storephase:         net.bodz.violet.store.StorePhase
    storel:             net.bodz.violet.store.StoreItem
    offstorel:          net.bodz.violet.store.OffStoreItem

    storeodr:           net.bodz.violet.store.StoreOrder
    storeodrl:          net.bodz.violet.store.StoreOrderItem
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
    column odr {
        javaName: order
    }
}
