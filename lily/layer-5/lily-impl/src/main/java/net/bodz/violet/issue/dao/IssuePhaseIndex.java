package net.bodz.violet.issue.dao;

import net.bodz.bas.meta.decl.ObjectType;
import net.bodz.lily.model.base.CoIndex;
import net.bodz.violet.issue.IssuePhase;

/**
* @label IssuePhase
*/
@ObjectType(IssuePhase.class)
public class IssuePhaseIndex
        extends CoIndex<IssuePhase, IssuePhaseCriteriaBuilder> {

    public IssuePhaseIndex() {
    }

}
