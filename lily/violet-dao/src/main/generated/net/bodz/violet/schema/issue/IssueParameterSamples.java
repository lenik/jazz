package net.bodz.violet.schema.issue;

import net.bodz.lily.schema.account.Group;
import net.bodz.lily.schema.account.User;
import net.bodz.lily.schema.account.dao.GroupMapper;
import net.bodz.lily.schema.account.dao.UserMapper;
import net.bodz.lily.test.TestSampleBuilder;
import net.bodz.lily.util.IRandomPicker;

public class IssueParameterSamples
        extends TestSampleBuilder {

    public User ownerUser;
    public Group ownerGroup;

    @Override
    public IssueParameter build()
            throws Exception {
        IssueParameter a = new IssueParameter();
        a.setOwnerUser(ownerUser);
        a.setOwnerGroup(ownerGroup);
        a.setDummy(139670088);
        return a;
    }

    @Override
    public IssueParameterSamples wireAny(IRandomPicker picker) {
        this.ownerUser = picker.pickAny(UserMapper.class, "user");
        this.ownerGroup = picker.pickAny(GroupMapper.class, "group");
        return this;
    }

    @Override
    public IssueParameter buildWired(IRandomPicker picker) throws Exception {
        return wireAny(picker).build();
    }

}
