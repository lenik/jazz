package net.bodz.violet.issue.impl;

import net.bodz.bas.meta.decl.ObjectType;
import net.bodz.lily.model.base.CoCategoryIndex;
import net.bodz.violet.issue.IssueCategory;

@ObjectType(IssueCategory.class)
public class IssueCategoryIndex
        extends CoCategoryIndex<IssueCategory, IssueCategoryMask> {

    public static final String SCHEMA = "issuecat";

    public IssueCategoryIndex() {
        super(SCHEMA);
    }

}
