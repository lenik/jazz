package net.bodz.lily.security.dao;

public class _GroupCriteriaBuilder_stuff<self_t extends _GroupCriteriaBuilder_stuff<self_t>>
        extends CoPrincipalCriteriaBuilder<self_t> {

    /** Group type like normal-group, role-group, etc. */
    public final IntegerField typeId = integer("\"type\"");

    /** The parent group. must be acyclic */
    public final IntegerField parentId = integer("parent");

}
