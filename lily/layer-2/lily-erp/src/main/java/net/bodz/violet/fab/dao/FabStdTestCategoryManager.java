package net.bodz.violet.fab.dao;

import net.bodz.bas.db.ctx.DataContext;
import net.bodz.lily.dao.AbstractEntityManager;
import net.bodz.violet.fab.FabStdTestCategory;

public class FabStdTestCategoryManager
        extends AbstractEntityManager<FabStdTestCategory, FabStdTestCategoryMapper> {

    public FabStdTestCategoryManager(DataContext dataContext) {
        super(dataContext, FabStdTestCategoryMapper.class);
    }

}
