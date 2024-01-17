package net.bodz.violet.edu.dao;

import net.bodz.lily.security.dao.UserMapper;
import net.bodz.lily.test.AbstractTableTest;
import net.bodz.violet.edu.TestQuestionVote;
import net.bodz.violet.edu.TestQuestionVoteSamples;

public class TestQuestionVoteMapperTest
        extends AbstractTableTest<TestQuestionVote, TestQuestionVoteCriteriaBuilder, TestQuestionVoteMapper> {

    @Override
    public TestQuestionVote buildSample()
            throws Exception {
        TestQuestionVoteSamples a = new TestQuestionVoteSamples();
        a.user = tables.pickAny(UserMapper.class, "user");
        a.parent = tables.pickAny(TestQuestionMapper.class, "testq");
        return a.build();
    }

}
