package net.bodz.violet.schema.tran.dao;

import net.bodz.bas.db.ctx.DataContext;
import net.bodz.lily.dao.AbstractEntityManager;
import net.bodz.violet.schema.tran.TransportOrder;

public class TransportOrderManager
        extends AbstractEntityManager<TransportOrder, TransportOrderMapper> {

    public TransportOrderManager(DataContext dataContext) {
        super(dataContext, TransportOrderMapper.class);
    }

}
