package net.bodz.violet.shop.dao;

import net.bodz.bas.db.ctx.DataContext;
import net.bodz.lily.dao.AbstractEntityManager;
import net.bodz.violet.shop.SalesOrder;

public class SalesOrderManager
        extends AbstractEntityManager<SalesOrder, SalesOrderMapper> {

    public SalesOrderManager(DataContext dataContext) {
        super(dataContext, SalesOrderMapper.class);
    }

}
