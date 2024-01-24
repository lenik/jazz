package net.bodz.violet.schema.edu.dao;

import net.bodz.bas.db.ctx.DataContext;
import net.bodz.lily.dao.AbstractEntityManager;
import net.bodz.violet.schema.edu.TestAnswer;

public class TestAnswerManager
        extends AbstractEntityManager<TestAnswer, TestAnswerMapper> {

    public TestAnswerManager(DataContext dataContext) {
        super(dataContext, TestAnswerMapper.class);
    }

}
