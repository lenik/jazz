package net.bodz.violet.edu.dao;

import net.bodz.bas.db.ctx.DataContext;
import net.bodz.lily.dao.AbstractEntityManager;
import net.bodz.violet.edu.TestQuestionTalkVote;

public class TestQuestionTalkVoteManager
        extends AbstractEntityManager<TestQuestionTalkVote, TestQuestionTalkVoteMapper> {

    public TestQuestionTalkVoteManager(DataContext dataContext) {
        super(dataContext, TestQuestionTalkVoteMapper.class);
    }

}
