package net.bodz.violet.issue.dao;

import net.bodz.bas.db.ctx.DataContext;
import net.bodz.lily.dao.AbstractEntityManager;
import net.bodz.violet.issue.IssueTag;

public class IssueTagManager
        extends AbstractEntityManager<IssueTag, IssueTagMapper> {

    public IssueTagManager(DataContext dataContext) {
        super(dataContext, IssueTagMapper.class);
    }

}
