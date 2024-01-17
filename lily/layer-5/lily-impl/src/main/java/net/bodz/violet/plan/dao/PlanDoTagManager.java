package net.bodz.violet.plan.dao;

import net.bodz.bas.db.ctx.DataContext;
import net.bodz.lily.dao.AbstractEntityManager;
import net.bodz.violet.plan.PlanDoTag;

public class PlanDoTagManager
        extends AbstractEntityManager<PlanDoTag, PlanDoTagMapper> {

    public PlanDoTagManager(DataContext dataContext) {
        super(dataContext, PlanDoTagMapper.class);
    }

}
