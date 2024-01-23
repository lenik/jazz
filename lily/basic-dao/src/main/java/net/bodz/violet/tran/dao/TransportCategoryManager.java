package net.bodz.violet.tran.dao;

import net.bodz.bas.db.ctx.DataContext;
import net.bodz.lily.dao.AbstractEntityManager;
import net.bodz.violet.tran.TransportCategory;

public class TransportCategoryManager
        extends AbstractEntityManager<TransportCategory, TransportCategoryMapper> {

    public TransportCategoryManager(DataContext dataContext) {
        super(dataContext, TransportCategoryMapper.class);
    }

}
