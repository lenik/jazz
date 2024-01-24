package net.bodz.violet.schema.edu;

import net.bodz.lily.schema.account.User;
import net.bodz.lily.schema.account.dao.UserMapper;
import net.bodz.lily.test.TestSampleBuilder;
import net.bodz.lily.util.IRandomPicker;
import net.bodz.violet.schema.edu.dao.TestQuestionTalkMapper;

public class TestQuestionTalkVoteSamples
        extends TestSampleBuilder {

    public User user;
    public TestQuestionTalk parent;

    @Override
    public TestQuestionTalkVote build()
            throws Exception {
        TestQuestionTalkVote a = new TestQuestionTalkVote();
        a.setUser(user);
        a.setParent(parent);
        a.setVoteScore(18358880);
        return a;
    }

    @Override
    public TestQuestionTalkVoteSamples wireAny(IRandomPicker picker) {
        this.user = picker.pickAny(UserMapper.class, "user");
        this.parent = picker.pickAny(TestQuestionTalkMapper.class, "testq_msg");
        return this;
    }

    @Override
    public TestQuestionTalkVote buildWired(IRandomPicker picker) throws Exception {
        return wireAny(picker).build();
    }

}
