package net.bodz.lily.contact;

import net.bodz.lily.contact.dao.PartyCategoryMapper;
import net.bodz.lily.security.Group;
import net.bodz.lily.security.User;
import net.bodz.lily.security.dao.GroupMapper;
import net.bodz.lily.security.dao.UserMapper;
import net.bodz.lily.test.TestSampleBuilder;
import net.bodz.lily.util.IRandomPicker;

public class PersonSamples
        extends TestSampleBuilder {

    public Group ownerGroup;
    public User ownerUser;
    public PartyCategory category;

    public Contact contact;

    @Override
    public Person build()
            throws Exception {
        Person a = new Person();
        a.setOwnerGroup(ownerGroup);
        a.setOwnerUser(ownerUser);
        a.setCategory(category);
        a.setRoleCount(36144224);
        a.setEmployee(true);
        a.setBankCount(141150881);
        a.setGender("p");
        a.setHomeland("Qmy;");
        a.setPassport("Jue");
        a.setSsn("Oeuy maenlu eaoaj!");
        a.setDln("Etui ro au oegra.");
        a.setContact(new ContactSamples().build());
        return a;
    }

    @Override
    public PersonSamples wireAny(IRandomPicker picker) {
        this.ownerGroup = picker.pickAny(GroupMapper.class, "group");
        this.ownerUser = picker.pickAny(UserMapper.class, "user");
        this.category = picker.pickAny(PartyCategoryMapper.class, "partycat");
        return this;
    }

    @Override
    public Person buildWired(IRandomPicker picker) throws Exception {
        return wireAny(picker).build();
    }

}
