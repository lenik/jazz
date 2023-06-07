package net.bodz.violet.edu;

import net.bodz.lily.security.User;
import net.bodz.lily.test.TestSampleBuilder;

public class TestQuestionFavSamples
        extends TestSampleBuilder {

    public TestQuestion testq;
    public User user;

    public TestQuestionFav build()
            throws Exception {
        TestQuestionFav a = new TestQuestionFav();
        a.setTestq(testq);
        a.setUser(user);
        return a;
    }

}
