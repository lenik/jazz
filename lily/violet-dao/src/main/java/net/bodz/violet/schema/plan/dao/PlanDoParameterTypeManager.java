package net.bodz.violet.schema.plan.dao;

import net.bodz.bas.db.ctx.DataContext;
import net.bodz.lily.dao.AbstractEntityManager;
import net.bodz.violet.schema.plan.PlanDoParameterType;

public class PlanDoParameterTypeManager
        extends AbstractEntityManager<PlanDoParameterType, PlanDoParameterTypeMapper> {

    public PlanDoParameterTypeManager(DataContext dataContext) {
        super(dataContext, PlanDoParameterTypeMapper.class);
    }

}
