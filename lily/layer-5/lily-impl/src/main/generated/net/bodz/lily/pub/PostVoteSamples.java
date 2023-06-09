package net.bodz.lily.pub;

import net.bodz.lily.security.User;
import net.bodz.lily.test.TestSampleBuilder;

public class PostVoteSamples
        extends TestSampleBuilder {

    public Post parent;
    public User user;

    public PostVote build()
            throws Exception {
        PostVote a = new PostVote();
        a.setParent(parent);
        a.setUser(user);
        a.setVoteScore(612466445);
        return a;
    }

}
