package net.bodz.lily.security.dao;

import net.bodz.bas.meta.decl.ObjectType;
import net.bodz.lily.model.base.CoIndex;
import net.bodz.lily.security.GroupType;

/**
* @label GroupType
*/
@ObjectType(GroupType.class)
public class GroupTypeIndex
        extends CoIndex<GroupType, GroupTypeCriteriaBuilder> {

    public GroupTypeIndex() {
    }

}
