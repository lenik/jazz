package net.bodz.violet.schema.shop.dao;

import net.bodz.bas.db.ctx.DataContext;
import net.bodz.lily.dao.AbstractEntityManager;
import net.bodz.violet.schema.shop.Shop;

public class ShopManager
        extends AbstractEntityManager<Shop, ShopMapper> {

    public ShopManager(DataContext dataContext) {
        super(dataContext, ShopMapper.class);
    }

}
