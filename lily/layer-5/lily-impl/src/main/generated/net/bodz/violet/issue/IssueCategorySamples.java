package net.bodz.violet.issue;

import net.bodz.lily.security.Group;
import net.bodz.lily.security.User;
import net.bodz.lily.security.dao.GroupMapper;
import net.bodz.lily.security.dao.UserMapper;
import net.bodz.lily.test.TestSampleBuilder;
import net.bodz.lily.util.IRandomPicker;
import net.bodz.violet.issue.dao.IssueCategoryMapper;

public class IssueCategorySamples
        extends TestSampleBuilder {

    public IssueCategory parent;
    public Group ownerGroup;
    public User ownerUser;

    @Override
    public IssueCategory build()
            throws Exception {
        IssueCategory a = new IssueCategory();
        a.setParent(parent);
        a.setOwnerGroup(ownerGroup);
        a.setOwnerUser(ownerUser);
        return a;
    }

    @Override
    public IssueCategorySamples wireAny(IRandomPicker picker) {
        this.parent = picker.pickAny(IssueCategoryMapper.class, "issuecat");
        this.ownerGroup = picker.pickAny(GroupMapper.class, "group");
        this.ownerUser = picker.pickAny(UserMapper.class, "user");
        return this;
    }

    @Override
    public IssueCategory buildWired(IRandomPicker picker) throws Exception {
        return wireAny(picker).build();
    }

}
