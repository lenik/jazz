package net.bodz.violet.issue;

import net.bodz.lily.security.Group;
import net.bodz.lily.security.User;
import net.bodz.lily.test.TestSampleBuilder;

public class IssuePhaseSamples
        extends TestSampleBuilder {

    public Group ownerGroup;
    public User ownerUser;

    public IssuePhase build()
            throws Exception {
        IssuePhase a = new IssuePhase();
        a.setOwnerGroup(ownerGroup);
        a.setOwnerUser(ownerUser);
        return a;
    }

}
