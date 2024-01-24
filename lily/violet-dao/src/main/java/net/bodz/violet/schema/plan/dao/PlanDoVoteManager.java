package net.bodz.violet.schema.plan.dao;

import net.bodz.bas.db.ctx.DataContext;
import net.bodz.lily.dao.AbstractEntityManager;
import net.bodz.violet.schema.plan.PlanDoVote;

public class PlanDoVoteManager
        extends AbstractEntityManager<PlanDoVote, PlanDoVoteMapper> {

    public PlanDoVoteManager(DataContext dataContext) {
        super(dataContext, PlanDoVoteMapper.class);
    }

}
