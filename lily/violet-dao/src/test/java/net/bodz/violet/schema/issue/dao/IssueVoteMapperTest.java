package net.bodz.violet.schema.issue.dao;

import net.bodz.lily.test.AbstractTableTest;
import net.bodz.violet.schema.issue.IssueVote;
import net.bodz.violet.schema.issue.IssueVoteSamples;

public class IssueVoteMapperTest
        extends AbstractTableTest<IssueVote, IssueVoteMapper> {

    @Override
    public IssueVote buildSample()
            throws Exception {
        IssueVoteSamples a = new IssueVoteSamples();
        return a.buildWired(tables);
    }

}
