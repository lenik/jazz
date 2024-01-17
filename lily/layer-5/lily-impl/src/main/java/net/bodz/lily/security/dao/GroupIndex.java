package net.bodz.lily.security.dao;

import net.bodz.bas.meta.decl.ObjectType;
import net.bodz.lily.model.base.CoIndex;
import net.bodz.lily.security.Group;

/**
* @label Group
*/
@ObjectType(Group.class)
public class GroupIndex
        extends CoIndex<Group, GroupCriteriaBuilder> {

    public GroupIndex() {
    }

}
