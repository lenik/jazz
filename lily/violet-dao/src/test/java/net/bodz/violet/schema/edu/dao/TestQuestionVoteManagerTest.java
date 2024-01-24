package net.bodz.violet.schema.edu.dao;

import net.bodz.lily.test.AbstractManagerTest;
import net.bodz.violet.schema.edu.TestQuestionVote;
import net.bodz.violet.schema.edu.TestQuestionVoteSamples;

public class TestQuestionVoteManagerTest
        extends AbstractManagerTest<TestQuestionVote, TestQuestionVoteMapper, TestQuestionVoteManager> {

    @Override
    public TestQuestionVote buildSample()
            throws Exception {
        TestQuestionVoteSamples a = new TestQuestionVoteSamples();
        return a.buildWired(tables);
    }

}
