package net.bodz.violet.schema.store.dao;

import net.bodz.bas.db.ctx.DataContext;
import net.bodz.lily.dao.AbstractEntityManager;
import net.bodz.violet.schema.store.StoreOrder;

public class StoreOrderManager
        extends AbstractEntityManager<StoreOrder, StoreOrderMapper> {

    public StoreOrderManager(DataContext dataContext) {
        super(dataContext, StoreOrderMapper.class);
    }

}
