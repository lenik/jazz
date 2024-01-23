package net.bodz.violet.store.dao;

import net.bodz.bas.db.ctx.DataContext;
import net.bodz.lily.dao.AbstractEntityManager;
import net.bodz.violet.store.StoreOrder;

public class StoreOrderManager
        extends AbstractEntityManager<StoreOrder, StoreOrderMapper> {

    public StoreOrderManager(DataContext dataContext) {
        super(dataContext, StoreOrderMapper.class);
    }

}
