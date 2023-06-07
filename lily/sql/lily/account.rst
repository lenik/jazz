#\import lily.base

class-map {
    net.bodz.lily.security.CoPrincipal: \
        group, \
        user
}

table-name {
    group:              net.bodz.lily.security.Group
    grouptype:          net.bodz.lily.security.GroupType
    user:               net.bodz.lily.security.User
    usertype:           net.bodz.lily.security.UserType
    usercat:            net.bodz.lily.security.UserCategory
    useroid:            net.bodz.lily.security.UserOtherId
    useroidtype:        net.bodz.lily.security.UserOtherIdType
    usersec:            net.bodz.lily.security.UserSecret
    user_run:           net.bodz.lily.security.UserRun
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
    }
}
