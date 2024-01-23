package net.bodz.violet.plan.dao;

import net.bodz.bas.db.ctx.DataContext;
import net.bodz.lily.dao.AbstractEntityManager;
import net.bodz.violet.plan.PlanDoVote;

public class PlanDoVoteManager
        extends AbstractEntityManager<PlanDoVote, PlanDoVoteMapper> {

    public PlanDoVoteManager(DataContext dataContext) {
        super(dataContext, PlanDoVoteMapper.class);
    }

}
