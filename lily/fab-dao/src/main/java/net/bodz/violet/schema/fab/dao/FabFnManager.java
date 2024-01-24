package net.bodz.violet.schema.fab.dao;

import net.bodz.bas.db.ctx.DataContext;
import net.bodz.lily.dao.AbstractEntityManager;
import net.bodz.violet.schema.fab.FabFn;

public class FabFnManager
        extends AbstractEntityManager<FabFn, FabFnMapper> {

    public FabFnManager(DataContext dataContext) {
        super(dataContext, FabFnMapper.class);
    }

}
