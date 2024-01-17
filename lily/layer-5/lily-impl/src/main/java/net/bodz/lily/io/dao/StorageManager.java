package net.bodz.lily.io.dao;

import net.bodz.bas.db.ctx.DataContext;
import net.bodz.lily.dao.AbstractEntityManager;
import net.bodz.lily.io.Storage;

public class StorageManager
        extends AbstractEntityManager<Storage, StorageMapper> {

    public StorageManager(DataContext dataContext) {
        super(dataContext, StorageMapper.class);
    }

}
