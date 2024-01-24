package net.bodz.violet.schema.fab.dao;

import net.bodz.bas.db.ctx.DataContext;
import net.bodz.lily.dao.AbstractEntityManager;
import net.bodz.violet.schema.fab.FabStdTest;

public class FabStdTestManager
        extends AbstractEntityManager<FabStdTest, FabStdTestMapper> {

    public FabStdTestManager(DataContext dataContext) {
        super(dataContext, FabStdTestMapper.class);
    }

}
