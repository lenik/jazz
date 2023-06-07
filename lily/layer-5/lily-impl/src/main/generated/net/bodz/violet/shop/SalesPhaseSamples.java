package net.bodz.violet.shop;

import net.bodz.lily.security.Group;
import net.bodz.lily.security.User;
import net.bodz.lily.test.TestSampleBuilder;

public class SalesPhaseSamples
        extends TestSampleBuilder {

    public Group ownerGroup;
    public User ownerUser;

    public SalesPhase build()
            throws Exception {
        SalesPhase a = new SalesPhase();
        a.setOwnerGroup(ownerGroup);
        a.setOwnerUser(ownerUser);
        return a;
    }

}
