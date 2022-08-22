package net.bodz.violet.edu.impl;

import net.bodz.lily.test.AbstractTableTest;
import net.bodz.violet.edu.TestQuestionVote;
import net.bodz.violet.edu.TestQuestionVoteSamples;

public class TestQuestionVoteMapperTest
        extends AbstractTableTest<TestQuestionVote, TestQuestionVoteMask, TestQuestionVoteMapper> {

    @Override
    public TestQuestionVote buildSample() {
        return TestQuestionVoteSamples.build();
    }

}
