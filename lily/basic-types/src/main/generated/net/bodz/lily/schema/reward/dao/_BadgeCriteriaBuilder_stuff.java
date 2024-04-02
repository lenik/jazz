package net.bodz.lily.schema.reward.dao;

import net.bodz.lily.concrete.CoImagedCriteriaBuilder;
import net.bodz.lily.entity.manager.ForEntityType;
import net.bodz.lily.schema.reward.Badge;

@ForEntityType(Badge.class)
public class _BadgeCriteriaBuilder_stuff<self_t extends _BadgeCriteriaBuilder_stuff<self_t>>
        extends CoImagedCriteriaBuilder<self_t> {

    public final IntegerField id = integer("id");

    public final StringField expr = string("expr");

    public final IntegerField val = integer("val");

    public final DiscreteField<int[]> levels = discrete("levels", int[].class);

    public final BooleanField descend = bool("descend");

    public final BooleanField transient_ = bool("transient");

    public final BooleanField indexed = bool("indexed");

}
