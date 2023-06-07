package net.bodz.violet.issue;

import net.bodz.lily.security.Group;
import net.bodz.lily.security.User;
import net.bodz.lily.test.TestSampleBuilder;

public class IssueParameterSamples
        extends TestSampleBuilder {

    public User ownerUser;
    public Group ownerGroup;

    public IssueParameter build()
            throws Exception {
        IssueParameter a = new IssueParameter();
        a.setOwnerUser(ownerUser);
        a.setOwnerGroup(ownerGroup);
        a.setDummy(139670088);
        return a;
    }

}
