#\import lily.security

class-map {
    net.bodz.lily.concrete.CoImaged: \
        badge
}

table-name {
    badge:              net.bodz.lily.schema.reward.Badge
    user_badge:         net.bodz.lily.schema.reward.UserBadge
}

table badge {
    column levels {
        javaType: int[]
    }
}
