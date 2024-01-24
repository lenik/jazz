package net.bodz.violet.schema.edu.dao;

import net.bodz.bas.db.ctx.DataContext;
import net.bodz.lily.dao.AbstractEntityManager;
import net.bodz.violet.schema.edu.TestPaperItem;

public class TestPaperItemManager
        extends AbstractEntityManager<TestPaperItem, TestPaperItemMapper> {

    public TestPaperItemManager(DataContext dataContext) {
        super(dataContext, TestPaperItemMapper.class);
    }

}
