package net.bodz.violet.issue.dao;

import net.bodz.lily.test.AbstractManagerTest;
import net.bodz.violet.issue.IssueVote;
import net.bodz.violet.issue.IssueVoteSamples;

public class IssueVoteManagerTest
        extends AbstractManagerTest<IssueVote, IssueVoteMapper, IssueVoteManager> {

    @Override
    public IssueVote buildSample()
            throws Exception {
        IssueVoteSamples a = new IssueVoteSamples();
        return a.buildWired(tables);
    }

}
