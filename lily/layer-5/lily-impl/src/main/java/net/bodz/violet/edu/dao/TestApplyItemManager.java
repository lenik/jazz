package net.bodz.violet.edu.dao;

import net.bodz.bas.db.ctx.DataContext;
import net.bodz.lily.dao.AbstractEntityManager;
import net.bodz.violet.edu.TestApplyItem;

public class TestApplyItemManager
        extends AbstractEntityManager<TestApplyItem, TestApplyItemMapper> {

    public TestApplyItemManager(DataContext dataContext) {
        super(dataContext, TestApplyItemMapper.class);
    }

}
