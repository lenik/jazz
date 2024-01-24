package net.bodz.violet.schema.issue.dao;

import net.bodz.lily.test.AbstractManagerTest;
import net.bodz.violet.schema.issue.IssueVote;
import net.bodz.violet.schema.issue.IssueVoteSamples;

public class IssueVoteManagerTest
        extends AbstractManagerTest<IssueVote, IssueVoteMapper, IssueVoteManager> {

    @Override
    public IssueVote buildSample()
            throws Exception {
        IssueVoteSamples a = new IssueVoteSamples();
        return a.buildWired(tables);
    }

}
