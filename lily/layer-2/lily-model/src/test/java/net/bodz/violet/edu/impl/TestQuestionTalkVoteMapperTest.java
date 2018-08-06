package net.bodz.violet.edu.impl;

import net.bodz.bas.db.ctx.DataContext;
import net.bodz.lily.test.AbstractMapperTest;
import net.bodz.violet.VioletTests;
import net.bodz.violet.edu.TestQuestionTalkVote;
import net.bodz.violet.edu.TestQuestionTalkVoteSamples;

public class TestQuestionTalkVoteMapperTest
        extends AbstractMapperTest<TestQuestionTalkVote, TestQuestionTalkVoteMask, TestQuestionTalkVoteMapper> {

    @Override
    public DataContext getContext() {
        return VioletTests.getDefaultContext();
    }

    @Override
    public TestQuestionTalkVote buildSample() {
        return TestQuestionTalkVoteSamples.build();
    }

}
