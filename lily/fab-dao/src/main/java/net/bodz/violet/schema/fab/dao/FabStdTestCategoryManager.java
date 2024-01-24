package net.bodz.violet.schema.fab.dao;

import net.bodz.bas.db.ctx.DataContext;
import net.bodz.lily.dao.AbstractEntityManager;
import net.bodz.violet.schema.fab.FabStdTestCategory;

public class FabStdTestCategoryManager
        extends AbstractEntityManager<FabStdTestCategory, FabStdTestCategoryMapper> {

    public FabStdTestCategoryManager(DataContext dataContext) {
        super(dataContext, FabStdTestCategoryMapper.class);
    }

}
