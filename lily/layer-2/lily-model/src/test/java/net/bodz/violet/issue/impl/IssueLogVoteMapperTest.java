package net.bodz.violet.issue.impl;

import net.bodz.bas.db.ctx.DataContext;
import net.bodz.lily.test.AbstractMapperTest;
import net.bodz.violet.VioletTests;
import net.bodz.violet.issue.IssueLogVote;
import net.bodz.violet.issue.IssueLogVoteSamples;

public class IssueLogVoteMapperTest
        extends AbstractMapperTest<IssueLogVote, IssueLogVoteMask, IssueLogVoteMapper> {

    @Override
    public DataContext getContext() {
        return VioletTests.getDefaultContext();
    }

    @Override
    public IssueLogVote buildSample() {
        return IssueLogVoteSamples.build();
    }

}
