package net.bodz.violet.edu.dao;

import net.bodz.bas.db.ctx.DataContext;
import net.bodz.lily.dao.AbstractEntityManager;
import net.bodz.violet.edu.TestPaperItem;

public class TestPaperItemManager
        extends AbstractEntityManager<TestPaperItem, TestPaperItemMapper> {

    public TestPaperItemManager(DataContext dataContext) {
        super(dataContext, TestPaperItemMapper.class);
    }

}
