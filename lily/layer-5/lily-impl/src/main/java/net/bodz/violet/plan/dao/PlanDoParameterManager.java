package net.bodz.violet.plan.dao;

import net.bodz.bas.db.ctx.DataContext;
import net.bodz.lily.dao.AbstractEntityManager;
import net.bodz.violet.plan.PlanDoParameter;

public class PlanDoParameterManager
        extends AbstractEntityManager<PlanDoParameter, PlanDoParameterMapper> {

    public PlanDoParameterManager(DataContext dataContext) {
        super(dataContext, PlanDoParameterMapper.class);
    }

}
