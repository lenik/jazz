package net.bodz.violet.schema.store.dao;

import net.bodz.bas.db.ctx.DataContext;
import net.bodz.lily.dao.AbstractEntityManager;
import net.bodz.violet.schema.store.OffStoreItem;

public class OffStoreItemManager
        extends AbstractEntityManager<OffStoreItem, OffStoreItemMapper> {

    public OffStoreItemManager(DataContext dataContext) {
        super(dataContext, OffStoreItemMapper.class);
    }

}
