package net.bodz.violet.edu.impl;

import net.bodz.lily.test.AbstractTableTest;
import net.bodz.violet.edu.TestQuestionTalkVote;
import net.bodz.violet.edu.TestQuestionTalkVoteSamples;

public class TestQuestionTalkVoteMapperTest
        extends AbstractTableTest<TestQuestionTalkVote, TestQuestionTalkVoteMask, TestQuestionTalkVoteMapper> {

    @Override
    public TestQuestionTalkVote buildSample() {
        return TestQuestionTalkVoteSamples.build();
    }

}
