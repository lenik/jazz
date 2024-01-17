package net.bodz.violet.plan.dao;

import net.bodz.bas.db.ctx.DataContext;
import net.bodz.lily.dao.AbstractEntityManager;
import net.bodz.violet.plan.PlanParty;

public class PlanPartyManager
        extends AbstractEntityManager<PlanParty, PlanPartyMapper> {

    public PlanPartyManager(DataContext dataContext) {
        super(dataContext, PlanPartyMapper.class);
    }

}
