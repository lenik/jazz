package net.bodz.lily.contact;

import net.bodz.lily.security.Group;
import net.bodz.lily.security.User;
import net.bodz.lily.test.TestSampleBuilder;

public class OrganizationSamples
        extends TestSampleBuilder {

    public PartyCategory category;
    public User ownerUser;
    public Group ownerGroup;

    public Contact contact;

    public Organization build()
            throws Exception {
        Organization a = new Organization();
        a.setCategory(category);
        a.setOwnerUser(ownerUser);
        a.setOwnerGroup(ownerGroup);
        a.setRoleCount(185886954);
        a.setBankCount(586844669);
        a.setSize(358966453);
        a.setTaxId("S maka auna fzoen.");
        a.setContact(new ContactSamples().build());
        return a;
    }

}
