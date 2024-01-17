package net.bodz.violet.edu.dao;

import net.bodz.bas.db.ctx.DataContext;
import net.bodz.lily.dao.AbstractEntityManager;
import net.bodz.violet.edu.TestQuestionTalk;

public class TestQuestionTalkManager
        extends AbstractEntityManager<TestQuestionTalk, TestQuestionTalkMapper> {

    public TestQuestionTalkManager(DataContext dataContext) {
        super(dataContext, TestQuestionTalkMapper.class);
    }

}
