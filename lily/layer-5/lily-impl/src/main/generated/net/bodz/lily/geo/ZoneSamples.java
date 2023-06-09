package net.bodz.lily.geo;

import net.bodz.lily.security.Group;
import net.bodz.lily.security.User;
import net.bodz.lily.test.TestSampleBuilder;

public class ZoneSamples
        extends TestSampleBuilder {

    public Zone parent;
    public User ownerUser;
    public Group ownerGroup;
    public ZoneCategory category;

    public Zone build()
            throws Exception {
        Zone a = new Zone();
        a.setParent(parent);
        a.setOwnerUser(ownerUser);
        a.setOwnerGroup(ownerGroup);
        a.setCategory(category);
        a.setId(8479118);
        a.setCode("Ei_okave");
        a.setCountry("");
        a.setDepth(185323507);
        a.setTelCode("oueuqooo.");
        a.setPostCode("");
        return a;
    }

}
