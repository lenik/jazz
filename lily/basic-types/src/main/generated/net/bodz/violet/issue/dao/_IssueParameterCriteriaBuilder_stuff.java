package net.bodz.violet.issue.dao;

import net.bodz.lily.model.base.CoCodeCriteriaBuilder;

public class _IssueParameterCriteriaBuilder_stuff<self_t extends _IssueParameterCriteriaBuilder_stuff<self_t>>
        extends CoCodeCriteriaBuilder<self_t> {

    public final IntegerField ownerUserId = integer("uid");

    public final IntegerField accessMode = integer("mode");

    public final IntegerField dummy = integer("dummy");

}
