package net.bodz.violet.edu.dao;

import net.bodz.bas.db.ctx.DataContext;
import net.bodz.lily.dao.AbstractEntityManager;
import net.bodz.violet.edu.TestApply;

public class TestApplyManager
        extends AbstractEntityManager<TestApply, TestApplyMapper> {

    public TestApplyManager(DataContext dataContext) {
        super(dataContext, TestApplyMapper.class);
    }

}
