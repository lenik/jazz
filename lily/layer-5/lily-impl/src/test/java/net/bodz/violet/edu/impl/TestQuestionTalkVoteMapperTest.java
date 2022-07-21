package net.bodz.violet.edu.impl;

import net.bodz.bas.db.ctx.DataContext;
import net.bodz.lily.test.AbstractMapperTest;
import net.bodz.violet.TestData;
import net.bodz.violet.edu.TestQuestionTalkVote;
import net.bodz.violet.edu.TestQuestionTalkVoteSamples;

public class TestQuestionTalkVoteMapperTest
        extends AbstractMapperTest<TestQuestionTalkVote, TestQuestionTalkVoteMask, TestQuestionTalkVoteMapper> {

    @Override
    public DataContext getContext() {
        return TestData.getDefaultContext();
    }

    @Override
    public TestQuestionTalkVote buildSample() {
        return TestQuestionTalkVoteSamples.build();
    }

}
