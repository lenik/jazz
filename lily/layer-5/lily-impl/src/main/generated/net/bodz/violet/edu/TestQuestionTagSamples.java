package net.bodz.violet.edu;

import net.bodz.lily.security.Group;
import net.bodz.lily.security.User;
import net.bodz.lily.test.TestSampleBuilder;

public class TestQuestionTagSamples
        extends TestSampleBuilder {

    public Group ownerGroup;
    public User ownerUser;
    public TestQuestionTag parent;

    public TestQuestionTag build()
            throws Exception {
        TestQuestionTag a = new TestQuestionTag();
        a.setOwnerGroup(ownerGroup);
        a.setOwnerUser(ownerUser);
        a.setParent(parent);
        return a;
    }

}
