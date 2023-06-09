package net.bodz.lily.pub;

import net.bodz.lily.security.User;
import net.bodz.lily.test.TestSampleBuilder;

public class ArticleTalkVoteSamples
        extends TestSampleBuilder {

    public User user;
    public ArticleTalk parent;

    public ArticleTalkVote build()
            throws Exception {
        ArticleTalkVote a = new ArticleTalkVote();
        a.setUser(user);
        a.setParent(parent);
        a.setVoteScore(878694475);
        return a;
    }

}
