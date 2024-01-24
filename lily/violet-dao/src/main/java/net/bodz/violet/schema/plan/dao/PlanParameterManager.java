package net.bodz.violet.schema.plan.dao;

import net.bodz.bas.db.ctx.DataContext;
import net.bodz.lily.dao.AbstractEntityManager;
import net.bodz.violet.schema.plan.PlanParameter;

public class PlanParameterManager
        extends AbstractEntityManager<PlanParameter, PlanParameterMapper> {

    public PlanParameterManager(DataContext dataContext) {
        super(dataContext, PlanParameterMapper.class);
    }

}
