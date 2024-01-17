package net.bodz.violet.issue.dao;

import net.bodz.bas.meta.decl.ObjectType;
import net.bodz.lily.model.base.CoIndex;
import net.bodz.violet.issue.IssueVote;

/**
* @label IssueVote
*/
@ObjectType(IssueVote.class)
public class IssueVoteIndex
        extends CoIndex<IssueVote, IssueVoteCriteriaBuilder> {

    public IssueVoteIndex() {
    }

}
