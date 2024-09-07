package net.bodz.violet.issue.dao;

import net.bodz.bas.meta.decl.ObjectType;
import net.bodz.lily.model.base.CoIndex;
import net.bodz.violet.issue.IssueTag;

/**
* @label IssueTag
*/
@ObjectType(IssueTag.class)
public class IssueTagIndex
        extends CoIndex<IssueTag, IssueTagCriteriaBuilder> {

    public IssueTagIndex() {
    }

}
