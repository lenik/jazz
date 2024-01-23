package net.bodz.violet.shop.dao;

import net.bodz.bas.db.ctx.DataContext;
import net.bodz.lily.dao.AbstractEntityManager;
import net.bodz.violet.shop.SalesPhase;

public class SalesPhaseManager
        extends AbstractEntityManager<SalesPhase, SalesPhaseMapper> {

    public SalesPhaseManager(DataContext dataContext) {
        super(dataContext, SalesPhaseMapper.class);
    }

}
