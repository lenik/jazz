package net.bodz.violet.schema.store.dao;

import net.bodz.bas.db.ctx.DataContext;
import net.bodz.lily.dao.AbstractEntityManager;
import net.bodz.violet.schema.store.StoreCategory;

public class StoreCategoryManager
        extends AbstractEntityManager<StoreCategory, StoreCategoryMapper> {

    public StoreCategoryManager(DataContext dataContext) {
        super(dataContext, StoreCategoryMapper.class);
    }

}
