package net.bodz.violet.edu;

import net.bodz.lily.security.User;
import net.bodz.lily.test.TestSampleBuilder;

public class TestQuestionTalkVoteSamples
        extends TestSampleBuilder {

    public User user;
    public TestQuestionTalk parent;

    public TestQuestionTalkVote build()
            throws Exception {
        TestQuestionTalkVote a = new TestQuestionTalkVote();
        a.setUser(user);
        a.setParent(parent);
        a.setVoteScore(18358880);
        return a;
    }

}
