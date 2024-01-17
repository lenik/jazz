package net.bodz.violet.fab.dao;

import net.bodz.bas.db.ctx.DataContext;
import net.bodz.lily.dao.AbstractEntityManager;
import net.bodz.violet.fab.FabStdTest;

public class FabStdTestManager
        extends AbstractEntityManager<FabStdTest, FabStdTestMapper> {

    public FabStdTestManager(DataContext dataContext) {
        super(dataContext, FabStdTestMapper.class);
    }

}
