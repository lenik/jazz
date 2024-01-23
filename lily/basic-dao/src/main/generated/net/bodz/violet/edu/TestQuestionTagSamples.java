package net.bodz.violet.edu;

import net.bodz.lily.security.Group;
import net.bodz.lily.security.User;
import net.bodz.lily.security.dao.GroupMapper;
import net.bodz.lily.security.dao.UserMapper;
import net.bodz.lily.test.TestSampleBuilder;
import net.bodz.lily.util.IRandomPicker;
import net.bodz.violet.edu.dao.TestQuestionTagMapper;

public class TestQuestionTagSamples
        extends TestSampleBuilder {

    public Group ownerGroup;
    public User ownerUser;
    public TestQuestionTag parent;

    @Override
    public TestQuestionTag build()
            throws Exception {
        TestQuestionTag a = new TestQuestionTag();
        a.setOwnerGroup(ownerGroup);
        a.setOwnerUser(ownerUser);
        a.setParent(parent);
        return a;
    }

    @Override
    public TestQuestionTagSamples wireAny(IRandomPicker picker) {
        this.ownerGroup = picker.pickAny(GroupMapper.class, "group");
        this.ownerUser = picker.pickAny(UserMapper.class, "user");
        this.parent = picker.pickAny(TestQuestionTagMapper.class, "testqtag");
        return this;
    }

    @Override
    public TestQuestionTag buildWired(IRandomPicker picker) throws Exception {
        return wireAny(picker).build();
    }

}
