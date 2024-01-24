package net.bodz.violet.schema.edu.dao;

import net.bodz.bas.db.ctx.DataContext;
import net.bodz.lily.dao.AbstractEntityManager;
import net.bodz.violet.schema.edu.TestQuestionTag;

public class TestQuestionTagManager
        extends AbstractEntityManager<TestQuestionTag, TestQuestionTagMapper> {

    public TestQuestionTagManager(DataContext dataContext) {
        super(dataContext, TestQuestionTagMapper.class);
    }

}
