package net.bodz.violet.issue.dao;

import net.bodz.bas.db.ctx.DataContext;
import net.bodz.lily.dao.AbstractEntityManager;
import net.bodz.violet.issue.IssuePhase;

public class IssuePhaseManager
        extends AbstractEntityManager<IssuePhase, IssuePhaseMapper> {

    public IssuePhaseManager(DataContext dataContext) {
        super(dataContext, IssuePhaseMapper.class);
    }

}
