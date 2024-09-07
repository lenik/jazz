#\import lily.account

column-property {
    uid:                ownerUserId
    gid:                ownerGroupId
    mode:               accessMode
}

class-map {
    net.bodz.lily.concrete.CoImaged: \
        policy
}

key-columns {
    format uid {
        pattern: ^uid$
        alias: ownerUser
        component: id
    }
    format gid {
        pattern: ^gid$
        alias: ownerGroup
        component: id
    }
}

table-name {
    policy:             net.bodz.lily.schema.account.Policy
}

table policy {
    column cclass {
        javaName: controlClass
    }
    column method {
        javaName: methodName
    }
    column allow {
        javaName: allowBits
    }
    column deny {
        javaName: denyBits
    }
}
