package net.bodz.violet.issue.impl;

import net.bodz.lily.test.AbstractTableTest;
import net.bodz.violet.issue.IssueVote;
import net.bodz.violet.issue.IssueVoteSamples;

public class IssueVoteMapperTest
        extends AbstractTableTest<IssueVote, IssueVoteMask, IssueVoteMapper> {

    @Override
    public IssueVote buildSample() {
        return IssueVoteSamples.build();
    }

}
