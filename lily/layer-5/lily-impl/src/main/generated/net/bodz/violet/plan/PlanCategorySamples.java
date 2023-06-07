package net.bodz.violet.plan;

import net.bodz.lily.security.Group;
import net.bodz.lily.security.User;
import net.bodz.lily.test.TestSampleBuilder;

public class PlanCategorySamples
        extends TestSampleBuilder {

    public PlanCategory parent;
    public User ownerUser;
    public Group ownerGroup;

    public PlanCategory build()
            throws Exception {
        PlanCategory a = new PlanCategory();
        a.setParent(parent);
        a.setOwnerUser(ownerUser);
        a.setOwnerGroup(ownerGroup);
        return a;
    }

}
