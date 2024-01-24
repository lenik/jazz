package net.bodz.violet.schema.shop.dao;

import net.bodz.bas.db.ctx.DataContext;
import net.bodz.lily.dao.AbstractEntityManager;
import net.bodz.violet.schema.shop.SalesOrderItem;

public class SalesOrderItemManager
        extends AbstractEntityManager<SalesOrderItem, SalesOrderItemMapper> {

    public SalesOrderItemManager(DataContext dataContext) {
        super(dataContext, SalesOrderItemMapper.class);
    }

}
