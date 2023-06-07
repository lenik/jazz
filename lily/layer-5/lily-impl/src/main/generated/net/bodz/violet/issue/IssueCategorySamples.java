package net.bodz.violet.issue;

import net.bodz.lily.security.Group;
import net.bodz.lily.security.User;
import net.bodz.lily.test.TestSampleBuilder;

public class IssueCategorySamples
        extends TestSampleBuilder {

    public IssueCategory parent;
    public Group ownerGroup;
    public User ownerUser;

    public IssueCategory build()
            throws Exception {
        IssueCategory a = new IssueCategory();
        a.setParent(parent);
        a.setOwnerGroup(ownerGroup);
        a.setOwnerUser(ownerUser);
        return a;
    }

}
