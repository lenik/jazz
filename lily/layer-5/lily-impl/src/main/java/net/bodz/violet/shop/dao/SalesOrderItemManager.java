package net.bodz.violet.shop.dao;

import net.bodz.bas.db.ctx.DataContext;
import net.bodz.lily.dao.AbstractEntityManager;
import net.bodz.violet.shop.SalesOrderItem;

public class SalesOrderItemManager
        extends AbstractEntityManager<SalesOrderItem, SalesOrderItemMapper> {

    public SalesOrderItemManager(DataContext dataContext) {
        super(dataContext, SalesOrderItemMapper.class);
    }

}
