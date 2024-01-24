package net.bodz.violet.schema.shop.dao;

import net.bodz.bas.db.ctx.DataContext;
import net.bodz.lily.dao.AbstractEntityManager;
import net.bodz.violet.schema.shop.ShopItemCategory;

public class ShopItemCategoryManager
        extends AbstractEntityManager<ShopItemCategory, ShopItemCategoryMapper> {

    public ShopItemCategoryManager(DataContext dataContext) {
        super(dataContext, ShopItemCategoryMapper.class);
    }

}
