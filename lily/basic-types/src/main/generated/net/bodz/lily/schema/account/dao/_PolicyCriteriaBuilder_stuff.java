package net.bodz.lily.schema.account.dao;

import net.bodz.lily.concrete.CoObjectCriteriaBuilder;

public class _PolicyCriteriaBuilder_stuff<self_t extends _PolicyCriteriaBuilder_stuff<self_t>>
        extends CoObjectCriteriaBuilder<self_t> {

    public final IntegerField id = integer("id");

    /** The policy name (unique) */
    public final StringField name = string("\"name\"");

    /** The control class */
    public final StringField controlClass = string("cclass");

    /** The method name */
    public final StringField methodName = string("\"method\"");

    /** allow */
    public final IntegerField allowBits = integer("allow");

    /** deny */
    public final IntegerField denyBits = integer("deny");

}
