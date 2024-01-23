package net.bodz.violet.edu.dao;

import net.bodz.lily.test.AbstractTableTest;
import net.bodz.violet.edu.TestQuestionTalkVote;
import net.bodz.violet.edu.TestQuestionTalkVoteSamples;

public class TestQuestionTalkVoteMapperTest
        extends AbstractTableTest<TestQuestionTalkVote, TestQuestionTalkVoteMapper> {

    @Override
    public TestQuestionTalkVote buildSample()
            throws Exception {
        TestQuestionTalkVoteSamples a = new TestQuestionTalkVoteSamples();
        return a.buildWired(tables);
    }

}
