package net.bodz.violet.schema.fab.dao;

import net.bodz.bas.db.ctx.DataContext;
import net.bodz.lily.dao.AbstractEntityManager;
import net.bodz.violet.schema.fab.FabOrder;

public class FabOrderManager
        extends AbstractEntityManager<FabOrder, FabOrderMapper> {

    public FabOrderManager(DataContext dataContext) {
        super(dataContext, FabOrderMapper.class);
    }

}
