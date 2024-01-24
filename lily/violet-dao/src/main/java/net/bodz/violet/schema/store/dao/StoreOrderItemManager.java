package net.bodz.violet.schema.store.dao;

import net.bodz.bas.db.ctx.DataContext;
import net.bodz.lily.dao.AbstractEntityManager;
import net.bodz.violet.schema.store.StoreOrderItem;

public class StoreOrderItemManager
        extends AbstractEntityManager<StoreOrderItem, StoreOrderItemMapper> {

    public StoreOrderItemManager(DataContext dataContext) {
        super(dataContext, StoreOrderItemMapper.class);
    }

}
