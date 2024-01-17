package net.bodz.violet.fab.dao;

import net.bodz.bas.db.ctx.DataContext;
import net.bodz.lily.dao.AbstractEntityManager;
import net.bodz.violet.fab.FabStdProcess;

public class FabStdProcessManager
        extends AbstractEntityManager<FabStdProcess, FabStdProcessMapper> {

    public FabStdProcessManager(DataContext dataContext) {
        super(dataContext, FabStdProcessMapper.class);
    }

}
