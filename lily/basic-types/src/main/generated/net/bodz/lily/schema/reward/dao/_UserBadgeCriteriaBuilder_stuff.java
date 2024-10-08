package net.bodz.lily.schema.reward.dao;

import net.bodz.lily.concrete.CoObjectCriteriaBuilder;
import net.bodz.lily.entity.manager.ForEntityType;
import net.bodz.lily.schema.reward.UserBadge;

@ForEntityType(UserBadge.class)
public class _UserBadgeCriteriaBuilder_stuff<self_t extends _UserBadgeCriteriaBuilder_stuff<self_t>>
        extends CoObjectCriteriaBuilder<self_t> {

    public final IntegerField id = integer("id");

    public final IntegerField userId = integer("\"user\"");

    public final IntegerField badgeId = integer("badge");

}
