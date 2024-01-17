package net.bodz.violet.shop.dao;

import net.bodz.bas.db.ctx.DataContext;
import net.bodz.lily.dao.AbstractEntityManager;
import net.bodz.violet.shop.ShopCategory;

public class ShopCategoryManager
        extends AbstractEntityManager<ShopCategory, ShopCategoryMapper> {

    public ShopCategoryManager(DataContext dataContext) {
        super(dataContext, ShopCategoryMapper.class);
    }

}
