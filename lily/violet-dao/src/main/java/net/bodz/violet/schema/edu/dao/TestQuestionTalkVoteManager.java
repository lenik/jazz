package net.bodz.violet.schema.edu.dao;

import net.bodz.bas.db.ctx.DataContext;
import net.bodz.lily.dao.AbstractEntityManager;
import net.bodz.violet.schema.edu.TestQuestionTalkVote;

public class TestQuestionTalkVoteManager
        extends AbstractEntityManager<TestQuestionTalkVote, TestQuestionTalkVoteMapper> {

    public TestQuestionTalkVoteManager(DataContext dataContext) {
        super(dataContext, TestQuestionTalkVoteMapper.class);
    }

}
