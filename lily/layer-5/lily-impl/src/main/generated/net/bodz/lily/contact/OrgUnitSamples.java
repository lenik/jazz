package net.bodz.lily.contact;

import net.bodz.lily.security.Group;
import net.bodz.lily.security.User;
import net.bodz.lily.test.TestSampleBuilder;

public class OrgUnitSamples
        extends TestSampleBuilder {

    public Organization org;
    public Group ownerGroup;
    public OrgUnit parent;
    public User ownerUser;

    public Contact contact;

    public OrgUnit build()
            throws Exception {
        OrgUnit a = new OrgUnit();
        a.setOrg(org);
        a.setOwnerGroup(ownerGroup);
        a.setParent(parent);
        a.setOwnerUser(ownerUser);
        a.setDepth(1090340487);
        a.setContact(new ContactSamples().build());
        return a;
    }

}
