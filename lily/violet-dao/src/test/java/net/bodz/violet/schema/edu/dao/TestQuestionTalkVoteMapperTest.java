package net.bodz.violet.schema.edu.dao;

import net.bodz.lily.test.AbstractTableTest;
import net.bodz.violet.schema.edu.TestQuestionTalkVote;
import net.bodz.violet.schema.edu.TestQuestionTalkVoteSamples;

public class TestQuestionTalkVoteMapperTest
        extends AbstractTableTest<TestQuestionTalkVote, TestQuestionTalkVoteMapper> {

    @Override
    public TestQuestionTalkVote buildSample()
            throws Exception {
        TestQuestionTalkVoteSamples a = new TestQuestionTalkVoteSamples();
        return a.buildWired(tables);
    }

}
