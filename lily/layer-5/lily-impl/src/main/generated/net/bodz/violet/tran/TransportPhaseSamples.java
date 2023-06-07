package net.bodz.violet.tran;

import net.bodz.lily.security.Group;
import net.bodz.lily.security.User;
import net.bodz.lily.test.TestSampleBuilder;

public class TransportPhaseSamples
        extends TestSampleBuilder {

    public Group ownerGroup;
    public User ownerUser;

    public TransportPhase build()
            throws Exception {
        TransportPhase a = new TransportPhase();
        a.setOwnerGroup(ownerGroup);
        a.setOwnerUser(ownerUser);
        return a;
    }

}
