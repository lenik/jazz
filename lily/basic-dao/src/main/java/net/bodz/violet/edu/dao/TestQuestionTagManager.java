package net.bodz.violet.edu.dao;

import net.bodz.bas.db.ctx.DataContext;
import net.bodz.lily.dao.AbstractEntityManager;
import net.bodz.violet.edu.TestQuestionTag;

public class TestQuestionTagManager
        extends AbstractEntityManager<TestQuestionTag, TestQuestionTagMapper> {

    public TestQuestionTagManager(DataContext dataContext) {
        super(dataContext, TestQuestionTagMapper.class);
    }

}
