package net.bodz.violet.plan.dao;

import net.bodz.bas.db.ctx.DataContext;
import net.bodz.lily.dao.AbstractEntityManager;
import net.bodz.violet.plan.PlanDo;

public class PlanDoManager
        extends AbstractEntityManager<PlanDo, PlanDoMapper> {

    public PlanDoManager(DataContext dataContext) {
        super(dataContext, PlanDoMapper.class);
    }

}
