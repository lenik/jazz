package net.bodz.lily.security.dao;

import net.bodz.lily.model.base.CoObjectCriteriaBuilder;

public class _PolicyCriteriaBuilder_stuff<self_t extends _PolicyCriteriaBuilder_stuff<self_t>>
        extends CoObjectCriteriaBuilder<self_t> {

    public final IntegerField id = integer("id");

    /** The control class */
    public final StringField controlClass = string("cclass");

    /** The method name */
    public final StringField methodName = string("\"method\"");

    /** allow */
    public final IntegerField allowBits = integer("allow");

    /** deny */
    public final IntegerField denyBits = integer("deny");

}
