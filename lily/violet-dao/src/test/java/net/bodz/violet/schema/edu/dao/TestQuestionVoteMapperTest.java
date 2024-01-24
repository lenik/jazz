package net.bodz.violet.schema.edu.dao;

import net.bodz.lily.test.AbstractTableTest;
import net.bodz.violet.schema.edu.TestQuestionVote;
import net.bodz.violet.schema.edu.TestQuestionVoteSamples;

public class TestQuestionVoteMapperTest
        extends AbstractTableTest<TestQuestionVote, TestQuestionVoteMapper> {

    @Override
    public TestQuestionVote buildSample()
            throws Exception {
        TestQuestionVoteSamples a = new TestQuestionVoteSamples();
        return a.buildWired(tables);
    }

}
