package net.bodz.lily.pub;

import net.bodz.lily.security.User;
import net.bodz.lily.test.TestSampleBuilder;

public class PostTalkVoteSamples
        extends TestSampleBuilder {

    public User user;
    public PostTalk parent;

    public PostTalkVote build()
            throws Exception {
        PostTalkVote a = new PostTalkVote();
        a.setUser(user);
        a.setParent(parent);
        a.setVoteScore(800046027);
        return a;
    }

}
