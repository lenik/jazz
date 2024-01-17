package net.bodz.violet.plan.dao;

import net.bodz.bas.db.ctx.DataContext;
import net.bodz.lily.dao.AbstractEntityManager;
import net.bodz.violet.plan.PlanFav;

public class PlanFavManager
        extends AbstractEntityManager<PlanFav, PlanFavMapper> {

    public PlanFavManager(DataContext dataContext) {
        super(dataContext, PlanFavMapper.class);
    }

}
