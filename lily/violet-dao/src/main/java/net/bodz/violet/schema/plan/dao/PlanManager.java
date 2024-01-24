package net.bodz.violet.schema.plan.dao;

import net.bodz.bas.db.ctx.DataContext;
import net.bodz.lily.dao.AbstractEntityManager;
import net.bodz.violet.schema.plan.Plan;

public class PlanManager
        extends AbstractEntityManager<Plan, PlanMapper> {

    public PlanManager(DataContext dataContext) {
        super(dataContext, PlanMapper.class);
    }

}
