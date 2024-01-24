package net.bodz.violet.schema.fab.dao;

import net.bodz.bas.db.ctx.DataContext;
import net.bodz.lily.dao.AbstractEntityManager;
import net.bodz.violet.schema.fab.FabProcess;

public class FabProcessManager
        extends AbstractEntityManager<FabProcess, FabProcessMapper> {

    public FabProcessManager(DataContext dataContext) {
        super(dataContext, FabProcessMapper.class);
    }

}
