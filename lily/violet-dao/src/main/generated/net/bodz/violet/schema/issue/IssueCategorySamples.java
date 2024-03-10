package net.bodz.violet.schema.issue;

import net.bodz.lily.schema.account.Group;
import net.bodz.lily.schema.account.User;
import net.bodz.lily.schema.account.dao.GroupMapper;
import net.bodz.lily.schema.account.dao.UserMapper;
import net.bodz.lily.test.TestSampleBuilder;
import net.bodz.lily.util.IRandomPicker;
import net.bodz.violet.schema.issue.dao.IssueCategoryMapper;

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
        a.setName("kugj edo_unerka i wikw");
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
