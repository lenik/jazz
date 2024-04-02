package net.bodz.lily.schema.account.dao;

import net.bodz.lily.concrete.CoImagedCriteriaBuilder;
import net.bodz.lily.entity.manager.ForEntityType;
import net.bodz.lily.schema.account.Group;

@ForEntityType(Group.class)
public class _GroupCriteriaBuilder_stuff<self_t extends _GroupCriteriaBuilder_stuff<self_t>>
        extends CoImagedCriteriaBuilder<self_t> {

    public final IntegerField id = integer("id");

    /** The group name (unique) */
    public final StringField name = string("\"name\"");

    /** Group type like normal-group, role-group, etc. */
    public final IntegerField typeId = integer("\"type\"");

    /** The parent group. must be acyclic */
    public final IntegerField parentId = integer("parent");

}
