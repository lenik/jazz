package net.bodz.violet.plan;

import net.bodz.lily.security.User;
import net.bodz.lily.test.TestSampleBuilder;

public class DiaryVoteSamples
        extends TestSampleBuilder {

    public Diary parent;
    public User user;

    public DiaryVote build()
            throws Exception {
        DiaryVote a = new DiaryVote();
        a.setParent(parent);
        a.setUser(user);
        a.setVoteScore(1833968484);
        return a;
    }

}
