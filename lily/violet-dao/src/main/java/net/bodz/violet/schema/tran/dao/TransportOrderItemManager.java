package net.bodz.violet.schema.tran.dao;

import net.bodz.bas.db.ctx.DataContext;
import net.bodz.lily.dao.AbstractEntityManager;
import net.bodz.violet.schema.tran.TransportOrderItem;

public class TransportOrderItemManager
        extends AbstractEntityManager<TransportOrderItem, TransportOrderItemMapper> {

    public TransportOrderItemManager(DataContext dataContext) {
        super(dataContext, TransportOrderItemMapper.class);
    }

}
