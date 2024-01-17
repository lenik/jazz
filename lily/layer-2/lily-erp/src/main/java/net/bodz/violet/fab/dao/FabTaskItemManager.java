package net.bodz.violet.fab.dao;

import net.bodz.bas.db.ctx.DataContext;
import net.bodz.lily.dao.AbstractEntityManager;
import net.bodz.violet.fab.FabTaskItem;

public class FabTaskItemManager
        extends AbstractEntityManager<FabTaskItem, FabTaskItemMapper> {

    public FabTaskItemManager(DataContext dataContext) {
        super(dataContext, FabTaskItemMapper.class);
    }

}
