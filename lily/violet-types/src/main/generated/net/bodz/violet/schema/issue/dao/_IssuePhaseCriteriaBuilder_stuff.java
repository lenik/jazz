package net.bodz.violet.schema.issue.dao;

import net.bodz.lily.concrete.CoCodeCriteriaBuilder;
import net.bodz.lily.entity.manager.ForEntityType;
import net.bodz.violet.schema.issue.IssuePhase;

@ForEntityType(IssuePhase.class)
public class _IssuePhaseCriteriaBuilder_stuff<self_t extends _IssuePhaseCriteriaBuilder_stuff<self_t>>
        extends CoCodeCriteriaBuilder<self_t> {

    public final IntegerField ownerUserId = integer("uid");

    public final IntegerField accessMode = integer("mode");

    public final IntegerField refCount = integer("nref");

}
