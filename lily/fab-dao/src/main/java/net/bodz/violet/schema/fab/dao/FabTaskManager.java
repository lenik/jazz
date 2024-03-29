package net.bodz.violet.schema.fab.dao;

import net.bodz.bas.db.ctx.DataContext;
import net.bodz.lily.dao.AbstractEntityManager;
import net.bodz.violet.schema.fab.FabTask;

public class FabTaskManager
        extends AbstractEntityManager<FabTask, FabTaskMapper> {

    public FabTaskManager(DataContext dataContext) {
        super(dataContext, FabTaskMapper.class);
    }

}
