package net.bodz.violet.issue;

import net.bodz.lily.security.User;
import net.bodz.lily.test.TestSampleBuilder;

public class IssueVoteSamples
        extends TestSampleBuilder {

    public User user;
    public Issue parent;

    public IssueVote build()
            throws Exception {
        IssueVote a = new IssueVote();
        a.setUser(user);
        a.setParent(parent);
        a.setVoteScore(409855229);
        return a;
    }

}
