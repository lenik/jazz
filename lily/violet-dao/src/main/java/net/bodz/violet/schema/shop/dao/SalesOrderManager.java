package net.bodz.violet.schema.shop.dao;

import net.bodz.bas.db.ctx.DataContext;
import net.bodz.lily.dao.AbstractEntityManager;
import net.bodz.violet.schema.shop.SalesOrder;

public class SalesOrderManager
        extends AbstractEntityManager<SalesOrder, SalesOrderMapper> {

    public SalesOrderManager(DataContext dataContext) {
        super(dataContext, SalesOrderMapper.class);
    }

}
