#\import lily.base

class-map {
    net.bodz.lily.concrete.CoCategory: \
        usercat
    net.bodz.lily.concrete.CoImaged: \
        grouptype, \
        usertype, \
        useroid, \
        useroidtype
    net.bodz.lily.concrete.CoPrincipal: \
        group, \
        user
}

table-name {
    group:              net.bodz.lily.schema.account.Group
    grouptype:          net.bodz.lily.schema.account.GroupType
    user:               net.bodz.lily.schema.account.User
    usertype:           net.bodz.lily.schema.account.UserType
    usercat:            net.bodz.lily.schema.account.UserCategory
    useroid:            net.bodz.lily.schema.account.UserOtherId
    useroidtype:        net.bodz.lily.schema.account.UserOtherIdType
    usersec:            net.bodz.lily.schema.account.UserSecret
    user_run:           net.bodz.lily.schema.account.UserRun
}

table user {
    column gid0 {
        javaName: primaryGroup
    }
}

table useroid {
    column oid {
        javaName: otherId
    }
}

table usersec {
    column passwd {
        javaName: password
    }
}

table user_run {
    column lastlog {
        javaName: lastLoginTime
    }
    column lastlogip {
        javaName: lastLoginIP
        javaType: java.net.InetAddress
    }
}
