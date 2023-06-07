package net.bodz.violet.issue.dao;

import net.bodz.bas.meta.decl.ObjectType;
import net.bodz.lily.model.base.CoIndex;
import net.bodz.violet.issue.IssueCategory;

/**
* @label IssueCategory
*/
@ObjectType(IssueCategory.class)
public class IssueCategoryIndex
        extends CoIndex<IssueCategory, IssueCategoryMask> {

    public IssueCategoryIndex() {
    }

}
