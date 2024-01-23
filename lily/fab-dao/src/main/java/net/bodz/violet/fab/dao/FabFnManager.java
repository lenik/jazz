package net.bodz.violet.fab.dao;

import net.bodz.bas.db.ctx.DataContext;
import net.bodz.lily.dao.AbstractEntityManager;
import net.bodz.violet.fab.FabFn;

public class FabFnManager
        extends AbstractEntityManager<FabFn, FabFnMapper> {

    public FabFnManager(DataContext dataContext) {
        super(dataContext, FabFnMapper.class);
    }

}
