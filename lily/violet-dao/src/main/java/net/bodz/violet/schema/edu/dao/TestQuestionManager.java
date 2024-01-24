package net.bodz.violet.schema.edu.dao;

import net.bodz.bas.db.ctx.DataContext;
import net.bodz.lily.dao.AbstractEntityManager;
import net.bodz.violet.schema.edu.TestQuestion;

public class TestQuestionManager
        extends AbstractEntityManager<TestQuestion, TestQuestionMapper> {

    public TestQuestionManager(DataContext dataContext) {
        super(dataContext, TestQuestionMapper.class);
    }

}
