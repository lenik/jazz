package net.bodz.violet.schema.fab.dao;

import net.bodz.bas.db.ctx.DataContext;
import net.bodz.lily.dao.AbstractEntityManager;
import net.bodz.violet.schema.fab.FabStdProcess;

public class FabStdProcessManager
        extends AbstractEntityManager<FabStdProcess, FabStdProcessMapper> {

    public FabStdProcessManager(DataContext dataContext) {
        super(dataContext, FabStdProcessMapper.class);
    }

}
