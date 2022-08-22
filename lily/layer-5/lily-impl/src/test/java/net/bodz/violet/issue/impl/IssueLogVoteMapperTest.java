package net.bodz.violet.issue.impl;

import net.bodz.lily.test.AbstractTableTest;
import net.bodz.violet.issue.IssueLogVote;
import net.bodz.violet.issue.IssueLogVoteSamples;

public class IssueLogVoteMapperTest
        extends AbstractTableTest<IssueLogVote, IssueLogVoteMask, IssueLogVoteMapper> {

    @Override
    public IssueLogVote buildSample() {
        return IssueLogVoteSamples.build();
    }

}
