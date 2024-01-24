package net.bodz.violet.schema.shop.dao;

import net.bodz.bas.db.ctx.DataContext;
import net.bodz.lily.dao.AbstractEntityManager;
import net.bodz.violet.schema.shop.ShopCategory;

public class ShopCategoryManager
        extends AbstractEntityManager<ShopCategory, ShopCategoryMapper> {

    public ShopCategoryManager(DataContext dataContext) {
        super(dataContext, ShopCategoryMapper.class);
    }

}
