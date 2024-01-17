package net.bodz.lily.reward.dao;

import net.bodz.bas.meta.decl.ObjectType;
import net.bodz.lily.model.base.CoIndex;
import net.bodz.lily.reward.Badge;

/**
* @label Badge
*/
@ObjectType(Badge.class)
public class BadgeIndex
        extends CoIndex<Badge, BadgeCriteriaBuilder> {

    public BadgeIndex() {
    }

}
