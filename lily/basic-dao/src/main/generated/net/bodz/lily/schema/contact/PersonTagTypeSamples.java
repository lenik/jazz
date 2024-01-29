package net.bodz.lily.schema.contact;

import net.bodz.lily.schema.account.Group;
import net.bodz.lily.schema.account.User;
import net.bodz.lily.schema.account.dao.GroupMapper;
import net.bodz.lily.schema.account.dao.UserMapper;
import net.bodz.lily.schema.contact.dao.PersonTagTypeMapper;
import net.bodz.lily.test.TestSampleBuilder;
import net.bodz.lily.util.IRandomPicker;

public class PersonTagTypeSamples
        extends TestSampleBuilder {

    public User ownerUser;
    public Group ownerGroup;
    public PersonTagType parent;

    @Override
    public PersonTagType build()
            throws Exception {
        PersonTagType a = new PersonTagType();
        a.setOwnerUser(ownerUser);
        a.setOwnerGroup(ownerGroup);
        a.setParent(parent);
        return a;
    }

    @Override
    public PersonTagTypeSamples wireAny(IRandomPicker picker) {
        this.ownerUser = picker.pickAny(UserMapper.class, "user");
        this.ownerGroup = picker.pickAny(GroupMapper.class, "group");
        this.parent = picker.pickAny(PersonTagTypeMapper.class, "persontag");
        return this;
    }

    @Override
    public PersonTagType buildWired(IRandomPicker picker) throws Exception {
        return wireAny(picker).build();
    }

}
