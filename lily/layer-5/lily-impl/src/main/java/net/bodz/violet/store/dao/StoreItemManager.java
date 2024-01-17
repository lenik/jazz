package net.bodz.violet.store.dao;

import net.bodz.bas.db.ctx.DataContext;
import net.bodz.lily.dao.AbstractEntityManager;
import net.bodz.violet.store.StoreItem;

public class StoreItemManager
        extends AbstractEntityManager<StoreItem, StoreItemMapper> {

    public StoreItemManager(DataContext dataContext) {
        super(dataContext, StoreItemMapper.class);
    }

}
