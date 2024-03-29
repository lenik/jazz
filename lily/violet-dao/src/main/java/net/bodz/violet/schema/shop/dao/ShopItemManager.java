package net.bodz.violet.schema.shop.dao;

import net.bodz.bas.db.ctx.DataContext;
import net.bodz.lily.dao.AbstractEntityManager;
import net.bodz.violet.schema.shop.ShopItem;

public class ShopItemManager
        extends AbstractEntityManager<ShopItem, ShopItemMapper> {

    public ShopItemManager(DataContext dataContext) {
        super(dataContext, ShopItemMapper.class);
    }

}
