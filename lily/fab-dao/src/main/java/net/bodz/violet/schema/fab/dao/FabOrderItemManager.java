package net.bodz.violet.schema.fab.dao;

import net.bodz.bas.db.ctx.DataContext;
import net.bodz.lily.dao.AbstractEntityManager;
import net.bodz.violet.schema.fab.FabOrderItem;

public class FabOrderItemManager
        extends AbstractEntityManager<FabOrderItem, FabOrderItemMapper> {

    public FabOrderItemManager(DataContext dataContext) {
        super(dataContext, FabOrderItemMapper.class);
    }

}
