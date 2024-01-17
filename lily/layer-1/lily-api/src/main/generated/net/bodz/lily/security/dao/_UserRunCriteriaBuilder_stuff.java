package net.bodz.lily.security.dao;

import net.bodz.lily.model.base.CoObjectCriteriaBuilder;

public class _UserRunCriteriaBuilder_stuff<self_t extends _UserRunCriteriaBuilder_stuff<self_t>>
        extends CoObjectCriteriaBuilder<self_t> {

    /** The user */
    public final IntegerField userId = integer("\"user\"");

    public final IntegerField score = integer("score");

    /** Last time of login */
    /** The source IP of last login */

}
