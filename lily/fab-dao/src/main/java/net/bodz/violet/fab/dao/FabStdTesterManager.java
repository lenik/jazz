package net.bodz.violet.fab.dao;

import net.bodz.bas.db.ctx.DataContext;
import net.bodz.lily.dao.AbstractEntityManager;
import net.bodz.violet.fab.FabStdTester;

public class FabStdTesterManager
        extends AbstractEntityManager<FabStdTester, FabStdTesterMapper> {

    public FabStdTesterManager(DataContext dataContext) {
        super(dataContext, FabStdTesterMapper.class);
    }

}
