package net.bodz.violet.issue.dao;

import net.bodz.bas.meta.decl.ObjectType;
import net.bodz.lily.model.base.CoIndex;
import net.bodz.violet.issue.IssueParameter;

/**
* @label IssueParameter
*/
@ObjectType(IssueParameter.class)
public class IssueParameterIndex
        extends CoIndex<IssueParameter, IssueParameterCriteriaBuilder> {

    public IssueParameterIndex() {
    }

}
