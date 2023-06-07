package net.bodz.violet.tran;

import net.bodz.lily.security.Group;
import net.bodz.lily.security.User;
import net.bodz.lily.test.TestSampleBuilder;

public class TransportCategorySamples
        extends TestSampleBuilder {

    public TransportCategory parent;
    public Group ownerGroup;
    public User ownerUser;

    public TransportCategory build()
            throws Exception {
        TransportCategory a = new TransportCategory();
        a.setParent(parent);
        a.setOwnerGroup(ownerGroup);
        a.setOwnerUser(ownerUser);
        return a;
    }

}
