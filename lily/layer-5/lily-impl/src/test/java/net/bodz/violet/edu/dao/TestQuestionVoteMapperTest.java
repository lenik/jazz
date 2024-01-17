package net.bodz.violet.edu.dao;

import net.bodz.lily.test.AbstractTableTest;
import net.bodz.violet.edu.TestQuestionVote;
import net.bodz.violet.edu.TestQuestionVoteSamples;

public class TestQuestionVoteMapperTest
        extends AbstractTableTest<TestQuestionVote, TestQuestionVoteMapper> {

    @Override
    public TestQuestionVote buildSample()
            throws Exception {
        TestQuestionVoteSamples a = new TestQuestionVoteSamples();
        return a.buildWired(tables);
    }

}
