package net.bodz.violet.issue.dao;

import net.bodz.lily.model.base.CoObjectCriteriaBuilder;

public class _IssueFavCriteriaBuilder_stuff<self_t extends _IssueFavCriteriaBuilder_stuff<self_t>>
        extends CoObjectCriteriaBuilder<self_t> {

    public final LongField id = _long("id");

    public final LongField issueId = _long("issue");

    public final IntegerField userId = integer("\"user\"");

}
