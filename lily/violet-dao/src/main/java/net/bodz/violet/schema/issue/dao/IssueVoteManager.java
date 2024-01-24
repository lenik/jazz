package net.bodz.violet.schema.issue.dao;

import net.bodz.bas.db.ctx.DataContext;
import net.bodz.lily.dao.AbstractEntityManager;
import net.bodz.violet.schema.issue.IssueVote;

public class IssueVoteManager
        extends AbstractEntityManager<IssueVote, IssueVoteMapper> {

    public IssueVoteManager(DataContext dataContext) {
        super(dataContext, IssueVoteMapper.class);
    }

}
