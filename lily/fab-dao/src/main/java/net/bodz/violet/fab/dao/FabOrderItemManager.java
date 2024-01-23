package net.bodz.violet.fab.dao;

import net.bodz.bas.db.ctx.DataContext;
import net.bodz.lily.dao.AbstractEntityManager;
import net.bodz.violet.fab.FabOrderItem;

public class FabOrderItemManager
        extends AbstractEntityManager<FabOrderItem, FabOrderItemMapper> {

    public FabOrderItemManager(DataContext dataContext) {
        super(dataContext, FabOrderItemMapper.class);
    }

}
