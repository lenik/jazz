package net.bodz.lily.inet;

import net.bodz.lily.security.Group;
import net.bodz.lily.security.User;
import net.bodz.lily.test.TestSampleBuilder;

public class ExternalSiteSamples
        extends TestSampleBuilder {

    public ExternalSite parent;
    public Group ownerGroup;
    public User ownerUser;

    public ExternalSite build()
            throws Exception {
        ExternalSite a = new ExternalSite();
        a.setParent(parent);
        a.setOwnerGroup(ownerGroup);
        a.setOwnerUser(ownerUser);
        a.setId(369647515);
        a.setDepth(283320390);
        a.setUrlfmt("ueca qko uxa*Afguo");
        a.setBonus(1684163652);
        a.setCount(2075354756);
        return a;
    }

}
