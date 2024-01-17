package net.bodz.lily.contact;

import net.bodz.lily.contact.dao.PartyCategoryMapper;
import net.bodz.lily.security.Group;
import net.bodz.lily.security.User;
import net.bodz.lily.security.dao.GroupMapper;
import net.bodz.lily.security.dao.UserMapper;
import net.bodz.lily.test.TestSampleBuilder;
import net.bodz.lily.util.IRandomPicker;

public class PartyCategorySamples
        extends TestSampleBuilder {

    public User ownerUser;
    public Group ownerGroup;
    public PartyCategory parent;

    @Override
    public PartyCategory build()
            throws Exception {
        PartyCategory a = new PartyCategory();
        a.setOwnerUser(ownerUser);
        a.setOwnerGroup(ownerGroup);
        a.setParent(parent);
        return a;
    }

    @Override
    public PartyCategorySamples wireAny(IRandomPicker picker) {
        this.ownerUser = picker.pickAny(UserMapper.class, "user");
        this.ownerGroup = picker.pickAny(GroupMapper.class, "group");
        this.parent = picker.pickAny(PartyCategoryMapper.class, "partycat");
        return this;
    }

    @Override
    public PartyCategory buildWired(IRandomPicker picker) throws Exception {
        return wireAny(picker).build();
    }

}
