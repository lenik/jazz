package net.bodz.violet.issue.impl;

import net.bodz.bas.db.ctx.DataContext;
import net.bodz.lily.test.AbstractMapperTest;
import net.bodz.violet.TestData;
import net.bodz.violet.issue.IssueVote;
import net.bodz.violet.issue.IssueVoteSamples;

public class IssueVoteMapperTest
        extends AbstractMapperTest<IssueVote, IssueVoteMask, IssueVoteMapper> {

    @Override
    public DataContext getContext() {
        return TestData.getDefaultContext();
    }

    @Override
    public IssueVote buildSample() {
        return IssueVoteSamples.build();
    }

}
