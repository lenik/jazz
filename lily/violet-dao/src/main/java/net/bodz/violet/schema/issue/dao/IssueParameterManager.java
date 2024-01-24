package net.bodz.violet.schema.issue.dao;

import net.bodz.bas.db.ctx.DataContext;
import net.bodz.lily.dao.AbstractEntityManager;
import net.bodz.violet.schema.issue.IssueParameter;

public class IssueParameterManager
        extends AbstractEntityManager<IssueParameter, IssueParameterMapper> {

    public IssueParameterManager(DataContext dataContext) {
        super(dataContext, IssueParameterMapper.class);
    }

}
