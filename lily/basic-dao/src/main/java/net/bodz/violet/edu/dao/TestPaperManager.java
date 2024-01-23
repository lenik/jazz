package net.bodz.violet.edu.dao;

import net.bodz.bas.db.ctx.DataContext;
import net.bodz.lily.dao.AbstractEntityManager;
import net.bodz.violet.edu.TestPaper;

public class TestPaperManager
        extends AbstractEntityManager<TestPaper, TestPaperMapper> {

    public TestPaperManager(DataContext dataContext) {
        super(dataContext, TestPaperMapper.class);
    }

}
