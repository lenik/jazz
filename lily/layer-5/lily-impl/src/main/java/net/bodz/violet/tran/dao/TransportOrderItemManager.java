package net.bodz.violet.tran.dao;

import net.bodz.bas.db.ctx.DataContext;
import net.bodz.lily.dao.AbstractEntityManager;
import net.bodz.violet.tran.TransportOrderItem;

public class TransportOrderItemManager
        extends AbstractEntityManager<TransportOrderItem, TransportOrderItemMapper> {

    public TransportOrderItemManager(DataContext dataContext) {
        super(dataContext, TransportOrderItemMapper.class);
    }

}
