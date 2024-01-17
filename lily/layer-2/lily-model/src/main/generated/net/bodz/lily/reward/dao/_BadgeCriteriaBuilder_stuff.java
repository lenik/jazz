package net.bodz.lily.reward.dao;

import net.bodz.lily.model.base.CoObjectCriteriaBuilder;

public class _BadgeCriteriaBuilder_stuff<self_t extends _BadgeCriteriaBuilder_stuff<self_t>>
        extends CoObjectCriteriaBuilder<self_t> {

    public final IntegerField id = integer("id");

    public final StringField expr = string("expr");

    public final IntegerField val = integer("val");

    public final BooleanField descend = bool("descend");

    public final BooleanField transient_ = bool("transient");

    public final BooleanField indexed = bool("indexed");

    public final StringField image = string("image");

}
