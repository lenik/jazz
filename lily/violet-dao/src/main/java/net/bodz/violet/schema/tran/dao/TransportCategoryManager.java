package net.bodz.violet.schema.tran.dao;

import net.bodz.bas.db.ctx.DataContext;
import net.bodz.lily.dao.AbstractEntityManager;
import net.bodz.violet.schema.tran.TransportCategory;

public class TransportCategoryManager
        extends AbstractEntityManager<TransportCategory, TransportCategoryMapper> {

    public TransportCategoryManager(DataContext dataContext) {
        super(dataContext, TransportCategoryMapper.class);
    }

}
