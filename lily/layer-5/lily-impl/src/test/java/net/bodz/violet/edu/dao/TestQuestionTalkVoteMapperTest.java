package net.bodz.violet.edu.dao;

import net.bodz.lily.security.dao.UserMapper;
import net.bodz.lily.test.AbstractTableTest;
import net.bodz.violet.edu.TestQuestionTalkVote;
import net.bodz.violet.edu.TestQuestionTalkVoteSamples;

public class TestQuestionTalkVoteMapperTest
        extends AbstractTableTest<TestQuestionTalkVote, TestQuestionTalkVoteMask, TestQuestionTalkVoteMapper> {

    @Override
    public TestQuestionTalkVote buildSample()
            throws Exception {
        TestQuestionTalkVoteSamples a = new TestQuestionTalkVoteSamples();
        a.user = tables.pickAny(UserMapper.class, "user");
        a.parent = tables.pickAny(TestQuestionTalkMapper.class, "testq_msg");
        return a.build();
    }

}
