package net.bodz.violet.schema.plan.dao;

import net.bodz.bas.db.ctx.DataContext;
import net.bodz.lily.dao.AbstractEntityManager;
import net.bodz.violet.schema.plan.PlanPhase;

public class PlanPhaseManager
        extends AbstractEntityManager<PlanPhase, PlanPhaseMapper> {

    public PlanPhaseManager(DataContext dataContext) {
        super(dataContext, PlanPhaseMapper.class);
    }

}
