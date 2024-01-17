package net.bodz.lily.contact;

import net.bodz.lily.contact.dao.PartyCategoryMapper;
import net.bodz.lily.security.Group;
import net.bodz.lily.security.User;
import net.bodz.lily.security.dao.GroupMapper;
import net.bodz.lily.security.dao.UserMapper;
import net.bodz.lily.test.TestSampleBuilder;
import net.bodz.lily.util.IRandomPicker;

public class OrganizationSamples
        extends TestSampleBuilder {

    public PartyCategory category;
    public User ownerUser;
    public Group ownerGroup;

    public Contact contact;

    @Override
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

    @Override
    public OrganizationSamples wireAny(IRandomPicker picker) {
        this.category = picker.pickAny(PartyCategoryMapper.class, "partycat");
        this.ownerUser = picker.pickAny(UserMapper.class, "user");
        this.ownerGroup = picker.pickAny(GroupMapper.class, "group");
        this.contact = new ContactSamples().build();
        return this;
    }

    @Override
    public Organization buildWired(IRandomPicker picker) throws Exception {
        return wireAny(picker).build();
    }

}
