package net.bodz.violet.schema.edu.dao;

import net.bodz.lily.test.AbstractManagerTest;
import net.bodz.violet.schema.edu.TestQuestionTalkVote;
import net.bodz.violet.schema.edu.TestQuestionTalkVoteSamples;

public class TestQuestionTalkVoteManagerTest
        extends AbstractManagerTest<TestQuestionTalkVote, TestQuestionTalkVoteMapper, TestQuestionTalkVoteManager> {

    @Override
    public TestQuestionTalkVote buildSample()
            throws Exception {
        TestQuestionTalkVoteSamples a = new TestQuestionTalkVoteSamples();
        return a.buildWired(tables);
    }

}
