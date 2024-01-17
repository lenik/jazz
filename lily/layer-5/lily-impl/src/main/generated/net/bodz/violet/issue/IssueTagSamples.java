package net.bodz.violet.issue;

import net.bodz.lily.security.Group;
import net.bodz.lily.security.User;
import net.bodz.lily.security.dao.GroupMapper;
import net.bodz.lily.security.dao.UserMapper;
import net.bodz.lily.test.TestSampleBuilder;
import net.bodz.lily.util.IRandomPicker;
import net.bodz.violet.issue.dao.IssueTagMapper;

public class IssueTagSamples
        extends TestSampleBuilder {

    public Group ownerGroup;
    public User ownerUser;
    public IssueTag parent;

    @Override
    public IssueTag build()
            throws Exception {
        IssueTag a = new IssueTag();
        a.setOwnerGroup(ownerGroup);
        a.setOwnerUser(ownerUser);
        a.setParent(parent);
        return a;
    }

    @Override
    public IssueTagSamples wireAny(IRandomPicker picker) {
        this.ownerGroup = picker.pickAny(GroupMapper.class, "group");
        this.ownerUser = picker.pickAny(UserMapper.class, "user");
        this.parent = picker.pickAny(IssueTagMapper.class, "issuetag");
        return this;
    }

    @Override
    public IssueTag buildWired(IRandomPicker picker) throws Exception {
        return wireAny(picker).build();
    }

}
