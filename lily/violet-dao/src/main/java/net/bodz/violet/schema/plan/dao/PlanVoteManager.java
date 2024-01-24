package net.bodz.violet.schema.plan.dao;

import net.bodz.bas.db.ctx.DataContext;
import net.bodz.lily.dao.AbstractEntityManager;
import net.bodz.violet.schema.plan.PlanVote;

public class PlanVoteManager
        extends AbstractEntityManager<PlanVote, PlanVoteMapper> {

    public PlanVoteManager(DataContext dataContext) {
        super(dataContext, PlanVoteMapper.class);
    }

}
