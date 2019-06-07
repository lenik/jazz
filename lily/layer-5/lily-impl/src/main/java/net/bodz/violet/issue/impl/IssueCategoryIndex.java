package net.bodz.violet.issue.impl;

import net.bodz.bas.meta.decl.ObjectType;
import net.bodz.lily.template.CoCategoryIndex;
import net.bodz.violet.issue.IssueCategory;

@ObjectType(IssueCategory.class)
public class IssueCategoryIndex
        extends CoCategoryIndex<IssueCategory, IssueCategoryMask> {

    public IssueCategoryIndex() {
    }

}
