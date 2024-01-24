package net.bodz.violet.schema.plan.dao;

import net.bodz.bas.db.ctx.DataContext;
import net.bodz.lily.dao.AbstractEntityManager;
import net.bodz.violet.schema.plan.PlanDoTag;

public class PlanDoTagManager
        extends AbstractEntityManager<PlanDoTag, PlanDoTagMapper> {

    public PlanDoTagManager(DataContext dataContext) {
        super(dataContext, PlanDoTagMapper.class);
    }

}
