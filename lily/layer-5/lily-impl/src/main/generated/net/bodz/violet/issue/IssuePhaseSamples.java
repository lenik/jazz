package net.bodz.violet.issue;

import net.bodz.lily.security.Group;
import net.bodz.lily.security.User;
import net.bodz.lily.security.dao.GroupMapper;
import net.bodz.lily.security.dao.UserMapper;
import net.bodz.lily.test.TestSampleBuilder;
import net.bodz.lily.util.IRandomPicker;

public class IssuePhaseSamples
        extends TestSampleBuilder {

    public Group ownerGroup;
    public User ownerUser;

    @Override
    public IssuePhase build()
            throws Exception {
        IssuePhase a = new IssuePhase();
        a.setOwnerGroup(ownerGroup);
        a.setOwnerUser(ownerUser);
        return a;
    }

    @Override
    public IssuePhaseSamples wireAny(IRandomPicker picker) {
        this.ownerGroup = picker.pickAny(GroupMapper.class, "group");
        this.ownerUser = picker.pickAny(UserMapper.class, "user");
        return this;
    }

    @Override
    public IssuePhase buildWired(IRandomPicker picker) throws Exception {
        return wireAny(picker).build();
    }

}
