package net.bodz.violet.schema.plan.dao;

import net.bodz.bas.db.ctx.DataContext;
import net.bodz.lily.dao.AbstractEntityManager;
import net.bodz.violet.schema.plan.PlanDo;

public class PlanDoManager
        extends AbstractEntityManager<PlanDo, PlanDoMapper> {

    public PlanDoManager(DataContext dataContext) {
        super(dataContext, PlanDoMapper.class);
    }

}
