package net.bodz.violet.plan.dao;

import net.bodz.bas.db.ctx.DataContext;
import net.bodz.lily.dao.AbstractEntityManager;
import net.bodz.violet.plan.PlanTag;

public class PlanTagManager
        extends AbstractEntityManager<PlanTag, PlanTagMapper> {

    public PlanTagManager(DataContext dataContext) {
        super(dataContext, PlanTagMapper.class);
    }

}
