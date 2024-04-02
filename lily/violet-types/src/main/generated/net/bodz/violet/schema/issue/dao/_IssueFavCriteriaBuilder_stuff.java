package net.bodz.violet.schema.issue.dao;

import net.bodz.lily.concrete.CoObjectCriteriaBuilder;
import net.bodz.lily.entity.manager.ForEntityType;
import net.bodz.violet.schema.issue.IssueFav;

@ForEntityType(IssueFav.class)
public class _IssueFavCriteriaBuilder_stuff<self_t extends _IssueFavCriteriaBuilder_stuff<self_t>>
        extends CoObjectCriteriaBuilder<self_t> {

    public final LongField id = _long("id");

    public final LongField issueId = _long("issue");

    public final IntegerField userId = integer("\"user\"");

}
