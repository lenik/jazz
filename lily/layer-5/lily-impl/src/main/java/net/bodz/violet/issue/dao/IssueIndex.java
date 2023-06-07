package net.bodz.violet.issue.dao;

import net.bodz.bas.meta.decl.ObjectType;
import net.bodz.lily.model.base.CoIndex;
import net.bodz.violet.issue.Issue;

/**
* @label Issue
*/
@ObjectType(Issue.class)
public class IssueIndex
        extends CoIndex<Issue, IssueMask> {

    public IssueIndex() {
    }

}
