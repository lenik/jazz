package net.bodz.violet.schema.edu.dao;

import net.bodz.bas.db.ctx.DataContext;
import net.bodz.lily.dao.AbstractEntityManager;
import net.bodz.violet.schema.edu.TestQuestionVote;

public class TestQuestionVoteManager
        extends AbstractEntityManager<TestQuestionVote, TestQuestionVoteMapper> {

    public TestQuestionVoteManager(DataContext dataContext) {
        super(dataContext, TestQuestionVoteMapper.class);
    }

}
