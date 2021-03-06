package net.bodz.violet.edu.impl;

import net.bodz.bas.db.ctx.DataContext;
import net.bodz.lily.test.AbstractMapperTest;
import net.bodz.violet.VioletTests;
import net.bodz.violet.edu.TestQuestionVote;
import net.bodz.violet.edu.TestQuestionVoteSamples;

public class TestQuestionVoteMapperTest
        extends AbstractMapperTest<TestQuestionVote, TestQuestionVoteMask, TestQuestionVoteMapper> {

    @Override
    public DataContext getContext() {
        return VioletTests.getDefaultContext();
    }

    @Override
    public TestQuestionVote buildSample() {
        return TestQuestionVoteSamples.build();
    }

}
