#\import lily.account

column-property {
    uid:                ownerUserId
    gid:                ownerGroupId
    mode:               accessMode
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
    policy:             net.bodz.lily.security.Policy
}
