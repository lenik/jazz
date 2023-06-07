#\import lily.security

column-property {
    art:                artifact
    qty:                quantity
}

class-map {
    net.bodz.violet.asset.AbstractAsset: \
        asset, \
        asset_group, \
        asset_org, \
        asset_ou, \
        asset_person, \
        asset_user
}

table-name {
    asset:              net.bodz.violet.asset.Asset
    asset_group:        net.bodz.violet.asset.GroupAsset
    asset_org:          net.bodz.violet.asset.OrgAsset
    asset_ou:           net.bodz.violet.asset.OrgUnitAsset
    asset_person:       net.bodz.violet.asset.PersonAsset
    asset_user:         net.bodz.violet.asset.UserAsset
}

table asset {
    column o_user {
        javaName: user
    }
    column o_group {
        javaName: group
    }
    column o_org {
        javaName: org
    }
    column o_orgunit {
        javaName: orgUnit
    }
    column o_person {
        javaName: person
    }
}
