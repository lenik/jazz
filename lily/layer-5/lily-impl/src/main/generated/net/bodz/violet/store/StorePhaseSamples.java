package net.bodz.violet.store;

import net.bodz.lily.security.Group;
import net.bodz.lily.security.User;
import net.bodz.lily.test.TestSampleBuilder;

public class StorePhaseSamples
        extends TestSampleBuilder {

    public User ownerUser;
    public Group ownerGroup;

    public StorePhase build()
            throws Exception {
        StorePhase a = new StorePhase();
        a.setOwnerUser(ownerUser);
        a.setOwnerGroup(ownerGroup);
        return a;
    }

}
