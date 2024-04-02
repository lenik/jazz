package net.bodz.lily.schema.inet.dao;

import net.bodz.lily.concrete.CoNodeCriteriaBuilder;
import net.bodz.lily.entity.manager.ForEntityType;
import net.bodz.lily.schema.inet.ExternalSite;

@ForEntityType(ExternalSite.class)
public class _ExternalSiteCriteriaBuilder_stuff<self_t extends _ExternalSiteCriteriaBuilder_stuff<self_t>>
        extends CoNodeCriteriaBuilder<self_t> {

    public final IntegerField ownerUserId = integer("uid");

    public final IntegerField accessMode = integer("mode");

    public final StringField urlfmt = string("urlfmt");

    public final IntegerField bonus = integer("bonus");

    public final IntegerField count = integer("\"count\"");

}
