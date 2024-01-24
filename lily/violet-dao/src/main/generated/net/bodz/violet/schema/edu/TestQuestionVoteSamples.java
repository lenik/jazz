package net.bodz.violet.schema.edu;

import net.bodz.lily.schema.account.User;
import net.bodz.lily.schema.account.dao.UserMapper;
import net.bodz.lily.test.TestSampleBuilder;
import net.bodz.lily.util.IRandomPicker;
import net.bodz.violet.schema.edu.dao.TestQuestionMapper;

public class TestQuestionVoteSamples
        extends TestSampleBuilder {

    public User user;
    public TestQuestion parent;

    @Override
    public TestQuestionVote build()
            throws Exception {
        TestQuestionVote a = new TestQuestionVote();
        a.setUser(user);
        a.setParent(parent);
        a.setVoteScore(906685851);
        return a;
    }

    @Override
    public TestQuestionVoteSamples wireAny(IRandomPicker picker) {
        this.user = picker.pickAny(UserMapper.class, "user");
        this.parent = picker.pickAny(TestQuestionMapper.class, "testq");
        return this;
    }

    @Override
    public TestQuestionVote buildWired(IRandomPicker picker) throws Exception {
        return wireAny(picker).build();
    }

}
