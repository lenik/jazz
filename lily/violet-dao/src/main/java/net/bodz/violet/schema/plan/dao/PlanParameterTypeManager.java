package net.bodz.violet.schema.plan.dao;

import net.bodz.bas.db.ctx.DataContext;
import net.bodz.lily.dao.AbstractEntityManager;
import net.bodz.violet.schema.plan.PlanParameterType;

public class PlanParameterTypeManager
        extends AbstractEntityManager<PlanParameterType, PlanParameterTypeMapper> {

    public PlanParameterTypeManager(DataContext dataContext) {
        super(dataContext, PlanParameterTypeMapper.class);
    }

}
