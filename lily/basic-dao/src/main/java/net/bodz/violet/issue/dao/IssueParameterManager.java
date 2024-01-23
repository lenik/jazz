package net.bodz.violet.issue.dao;

import net.bodz.bas.db.ctx.DataContext;
import net.bodz.lily.dao.AbstractEntityManager;
import net.bodz.violet.issue.IssueParameter;

public class IssueParameterManager
        extends AbstractEntityManager<IssueParameter, IssueParameterMapper> {

    public IssueParameterManager(DataContext dataContext) {
        super(dataContext, IssueParameterMapper.class);
    }

}
