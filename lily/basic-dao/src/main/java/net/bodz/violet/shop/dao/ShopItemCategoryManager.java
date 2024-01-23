package net.bodz.violet.shop.dao;

import net.bodz.bas.db.ctx.DataContext;
import net.bodz.lily.dao.AbstractEntityManager;
import net.bodz.violet.shop.ShopItemCategory;

public class ShopItemCategoryManager
        extends AbstractEntityManager<ShopItemCategory, ShopItemCategoryMapper> {

    public ShopItemCategoryManager(DataContext dataContext) {
        super(dataContext, ShopItemCategoryMapper.class);
    }

}
