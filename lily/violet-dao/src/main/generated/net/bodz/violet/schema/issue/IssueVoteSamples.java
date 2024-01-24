package net.bodz.violet.schema.issue;

import net.bodz.lily.schema.account.User;
import net.bodz.lily.schema.account.dao.UserMapper;
import net.bodz.lily.test.TestSampleBuilder;
import net.bodz.lily.util.IRandomPicker;
import net.bodz.violet.schema.issue.dao.IssueMapper;

public class IssueVoteSamples
        extends TestSampleBuilder {

    public User user;
    public Issue parent;

    @Override
    public IssueVote build()
            throws Exception {
        IssueVote a = new IssueVote();
        a.setUser(user);
        a.setParent(parent);
        a.setVoteScore(409855229);
        return a;
    }

    @Override
    public IssueVoteSamples wireAny(IRandomPicker picker) {
        this.user = picker.pickAny(UserMapper.class, "user");
        this.parent = picker.pickAny(IssueMapper.class, "issue");
        return this;
    }

    @Override
    public IssueVote buildWired(IRandomPicker picker) throws Exception {
        return wireAny(picker).build();
    }

}
