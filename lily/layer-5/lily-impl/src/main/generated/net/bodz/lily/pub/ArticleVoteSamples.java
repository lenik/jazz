package net.bodz.lily.pub;

import net.bodz.lily.security.User;
import net.bodz.lily.test.TestSampleBuilder;

public class ArticleVoteSamples
        extends TestSampleBuilder {

    public Article parent;
    public User user;

    public ArticleVote build()
            throws Exception {
        ArticleVote a = new ArticleVote();
        a.setParent(parent);
        a.setUser(user);
        a.setVoteScore(1607102641);
        return a;
    }

}
