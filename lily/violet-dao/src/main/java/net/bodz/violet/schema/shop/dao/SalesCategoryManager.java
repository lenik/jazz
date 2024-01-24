package net.bodz.violet.schema.shop.dao;

import net.bodz.bas.db.ctx.DataContext;
import net.bodz.lily.dao.AbstractEntityManager;
import net.bodz.violet.schema.shop.SalesCategory;

public class SalesCategoryManager
        extends AbstractEntityManager<SalesCategory, SalesCategoryMapper> {

    public SalesCategoryManager(DataContext dataContext) {
        super(dataContext, SalesCategoryMapper.class);
    }

}
