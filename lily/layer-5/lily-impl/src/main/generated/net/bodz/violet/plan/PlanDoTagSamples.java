package net.bodz.violet.plan;

import net.bodz.lily.security.Group;
import net.bodz.lily.security.User;
import net.bodz.lily.test.TestSampleBuilder;

public class PlanDoTagSamples
        extends TestSampleBuilder {

    public Group ownerGroup;
    public PlanDoTag parent;
    public User ownerUser;

    public PlanDoTag build()
            throws Exception {
        PlanDoTag a = new PlanDoTag();
        a.setOwnerGroup(ownerGroup);
        a.setParent(parent);
        a.setOwnerUser(ownerUser);
        return a;
    }

}
