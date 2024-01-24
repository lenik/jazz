package net.bodz.violet.schema.edu.dao;

import net.bodz.bas.db.ctx.DataContext;
import net.bodz.lily.dao.AbstractEntityManager;
import net.bodz.violet.schema.edu.TestQuestionTalk;

public class TestQuestionTalkManager
        extends AbstractEntityManager<TestQuestionTalk, TestQuestionTalkMapper> {

    public TestQuestionTalkManager(DataContext dataContext) {
        super(dataContext, TestQuestionTalkMapper.class);
    }

}
