package net.bodz.violet.issue;

import net.bodz.lily.security.Group;
import net.bodz.lily.security.User;
import net.bodz.lily.test.TestSampleBuilder;

public class IssueTagSamples
        extends TestSampleBuilder {

    public Group ownerGroup;
    public User ownerUser;
    public IssueTag parent;

    public IssueTag build()
            throws Exception {
        IssueTag a = new IssueTag();
        a.setOwnerGroup(ownerGroup);
        a.setOwnerUser(ownerUser);
        a.setParent(parent);
        return a;
    }

}
