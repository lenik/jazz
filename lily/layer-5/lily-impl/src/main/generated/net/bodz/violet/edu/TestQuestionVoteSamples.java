package net.bodz.violet.edu;

import net.bodz.lily.security.User;
import net.bodz.lily.test.TestSampleBuilder;

public class TestQuestionVoteSamples
        extends TestSampleBuilder {

    public User user;
    public TestQuestion parent;

    public TestQuestionVote build()
            throws Exception {
        TestQuestionVote a = new TestQuestionVote();
        a.setUser(user);
        a.setParent(parent);
        a.setVoteScore(906685851);
        return a;
    }

}
