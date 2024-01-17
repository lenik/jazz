package net.bodz.violet.plan.dao;

import net.bodz.bas.db.ctx.DataContext;
import net.bodz.lily.dao.AbstractEntityManager;
import net.bodz.violet.plan.PlanParameter;

public class PlanParameterManager
        extends AbstractEntityManager<PlanParameter, PlanParameterMapper> {

    public PlanParameterManager(DataContext dataContext) {
        super(dataContext, PlanParameterMapper.class);
    }

}
