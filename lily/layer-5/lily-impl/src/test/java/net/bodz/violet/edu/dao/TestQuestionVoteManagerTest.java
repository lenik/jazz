package net.bodz.violet.edu.dao;

import net.bodz.lily.test.AbstractManagerTest;
import net.bodz.violet.edu.TestQuestionVote;
import net.bodz.violet.edu.TestQuestionVoteSamples;

public class TestQuestionVoteManagerTest
        extends AbstractManagerTest<TestQuestionVote, TestQuestionVoteMapper, TestQuestionVoteManager> {

    @Override
    public TestQuestionVote buildSample()
            throws Exception {
        TestQuestionVoteSamples a = new TestQuestionVoteSamples();
        return a.buildWired(tables);
    }

}
