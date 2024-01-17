package net.bodz.lily.security.dao;

import net.bodz.lily.model.base.CoObjectCriteriaBuilder;

public class _UserOtherIdCriteriaBuilder_stuff<self_t extends _UserOtherIdCriteriaBuilder_stuff<self_t>>
        extends CoObjectCriteriaBuilder<self_t> {

    public final IntegerField id = integer("id");

    public final IntegerField year = integer("\"year\"");

    /** The declaring user */
    public final IntegerField userId = integer("\"user\"");

    /** Type of Open ID */
    public final IntegerField typeId = integer("\"type\"");

    /** The identity data */
    public final StringField otherId = string("oid");

    /** The authentication data */

}
