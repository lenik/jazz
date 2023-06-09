package net.bodz.lily.vapp;

import net.bodz.lily.security.Group;
import net.bodz.lily.security.User;
import net.bodz.lily.test.TestSampleBuilder;

public class VAppCatSamples
        extends TestSampleBuilder {

    public Group ownerGroup;
    public User ownerUser;
    public VAppCat parent;

    public VAppCat build()
            throws Exception {
        VAppCat a = new VAppCat();
        a.setOwnerGroup(ownerGroup);
        a.setOwnerUser(ownerUser);
        a.setParent(parent);
        a.setId(1191060333);
        a.setDepth(1976022062);
        a.setRefCount(1067875411);
        return a;
    }

}
