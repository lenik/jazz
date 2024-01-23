package net.bodz.violet.issue.dao;

import net.bodz.bas.db.ctx.DataContext;
import net.bodz.lily.dao.AbstractEntityManager;
import net.bodz.violet.issue.Issue;

public class IssueManager
        extends AbstractEntityManager<Issue, IssueMapper> {

    public IssueManager(DataContext dataContext) {
        super(dataContext, IssueMapper.class);
    }

}
