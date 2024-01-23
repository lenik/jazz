package net.bodz.violet.plan.dao;

import net.bodz.bas.db.ctx.DataContext;
import net.bodz.lily.dao.AbstractEntityManager;
import net.bodz.violet.plan.PlanCategory;

public class PlanCategoryManager
        extends AbstractEntityManager<PlanCategory, PlanCategoryMapper> {

    public PlanCategoryManager(DataContext dataContext) {
        super(dataContext, PlanCategoryMapper.class);
    }

}
