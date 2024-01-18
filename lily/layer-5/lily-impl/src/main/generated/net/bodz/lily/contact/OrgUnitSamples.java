package net.bodz.lily.contact;

import net.bodz.lily.contact.dao.OrgUnitMapper;
import net.bodz.lily.contact.dao.OrganizationMapper;
import net.bodz.lily.security.Group;
import net.bodz.lily.security.User;
import net.bodz.lily.security.dao.GroupMapper;
import net.bodz.lily.security.dao.UserMapper;
import net.bodz.lily.test.TestSampleBuilder;
import net.bodz.lily.util.IRandomPicker;

public class OrgUnitSamples
        extends TestSampleBuilder {

    public Organization org;
    public Group ownerGroup;
    public OrgUnit parent;
    public User ownerUser;

    public Contact contact;

    @Override
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

    @Override
    public OrgUnitSamples wireAny(IRandomPicker picker) {
        this.org = picker.pickAny(OrganizationMapper.class, "org");
        this.ownerGroup = picker.pickAny(GroupMapper.class, "group");
        this.parent = picker.pickAny(OrgUnitMapper.class, "orgunit");
        this.ownerUser = picker.pickAny(UserMapper.class, "user");
        return this;
    }

    @Override
    public OrgUnit buildWired(IRandomPicker picker) throws Exception {
        return wireAny(picker).build();
    }

}
