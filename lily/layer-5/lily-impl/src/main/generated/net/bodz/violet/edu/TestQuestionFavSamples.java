package net.bodz.violet.edu;

import net.bodz.lily.security.User;
import net.bodz.lily.security.dao.UserMapper;
import net.bodz.lily.test.TestSampleBuilder;
import net.bodz.lily.util.IRandomPicker;
import net.bodz.violet.edu.dao.TestQuestionMapper;

public class TestQuestionFavSamples
        extends TestSampleBuilder {

    public TestQuestion testq;
    public User user;

    @Override
    public TestQuestionFav build()
            throws Exception {
        TestQuestionFav a = new TestQuestionFav();
        a.setTestq(testq);
        a.setUser(user);
        return a;
    }

    @Override
    public TestQuestionFavSamples wireAny(IRandomPicker picker) {
        this.testq = picker.pickAny(TestQuestionMapper.class, "testq");
        this.user = picker.pickAny(UserMapper.class, "user");
        return this;
    }

    @Override
    public TestQuestionFav buildWired(IRandomPicker picker) throws Exception {
        return wireAny(picker).build();
    }

}
