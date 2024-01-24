package net.bodz.violet.schema.store.dao;

import net.bodz.bas.db.ctx.DataContext;
import net.bodz.lily.dao.AbstractEntityManager;
import net.bodz.violet.schema.store.StorePhase;

public class StorePhaseManager
        extends AbstractEntityManager<StorePhase, StorePhaseMapper> {

    public StorePhaseManager(DataContext dataContext) {
        super(dataContext, StorePhaseMapper.class);
    }

}
