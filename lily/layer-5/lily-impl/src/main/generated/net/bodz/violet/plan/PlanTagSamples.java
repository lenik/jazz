package net.bodz.violet.plan;

import net.bodz.lily.security.Group;
import net.bodz.lily.security.User;
import net.bodz.lily.test.TestSampleBuilder;

public class PlanTagSamples
        extends TestSampleBuilder {

    public User ownerUser;
    public Group ownerGroup;
    public PlanTag parent;

    public PlanTag build()
            throws Exception {
        PlanTag a = new PlanTag();
        a.setOwnerUser(ownerUser);
        a.setOwnerGroup(ownerGroup);
        a.setParent(parent);
        return a;
    }

}
