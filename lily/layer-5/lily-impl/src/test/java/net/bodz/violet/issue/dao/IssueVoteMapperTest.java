package net.bodz.violet.issue.dao;

import net.bodz.lily.security.dao.UserMapper;
import net.bodz.lily.test.AbstractTableTest;
import net.bodz.violet.issue.IssueVote;
import net.bodz.violet.issue.IssueVoteSamples;

public class IssueVoteMapperTest
        extends AbstractTableTest<IssueVote, IssueVoteCriteriaBuilder, IssueVoteMapper> {

    @Override
    public IssueVote buildSample()
            throws Exception {
        IssueVoteSamples a = new IssueVoteSamples();
        a.user = tables.pickAny(UserMapper.class, "user");
        a.parent = tables.pickAny(IssueMapper.class, "issue");
        return a.build();
    }

}
