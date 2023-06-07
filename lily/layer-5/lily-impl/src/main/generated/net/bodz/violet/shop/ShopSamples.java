package net.bodz.violet.shop;

import net.bodz.lily.security.Group;
import net.bodz.lily.security.User;
import net.bodz.lily.test.TestSampleBuilder;

public class ShopSamples
        extends TestSampleBuilder {

    public Group ownerGroup;
    public User ownerUser;

    public Shop build()
            throws Exception {
        Shop a = new Shop();
        a.setOwnerGroup(ownerGroup);
        a.setOwnerUser(ownerUser);
        a.setId(1588425089);
        a.setCode("Xoc! qj plol; xyi u, ujp-poesr");
        a.setHydm(1929075191);
        return a;
    }

}
