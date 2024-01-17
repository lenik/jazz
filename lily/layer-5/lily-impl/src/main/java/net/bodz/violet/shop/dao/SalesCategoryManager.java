package net.bodz.violet.shop.dao;

import net.bodz.bas.db.ctx.DataContext;
import net.bodz.lily.dao.AbstractEntityManager;
import net.bodz.violet.shop.SalesCategory;

public class SalesCategoryManager
        extends AbstractEntityManager<SalesCategory, SalesCategoryMapper> {

    public SalesCategoryManager(DataContext dataContext) {
        super(dataContext, SalesCategoryMapper.class);
    }

}
