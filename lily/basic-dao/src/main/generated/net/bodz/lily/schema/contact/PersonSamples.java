package net.bodz.lily.schema.contact;

import net.bodz.lily.schema.account.Group;
import net.bodz.lily.schema.account.User;
import net.bodz.lily.schema.account.dao.GroupMapper;
import net.bodz.lily.schema.account.dao.UserMapper;
import net.bodz.lily.schema.contact.dao.PartyCategoryMapper;
import net.bodz.lily.schema.contact.dao.PersonMapper;
import net.bodz.lily.test.TestSampleBuilder;
import net.bodz.lily.util.IRandomPicker;

public class PersonSamples
        extends TestSampleBuilder {

    public Person mother;
    public Group ownerGroup;
    public Person father;
    public User ownerUser;
    public PartyCategory category;

    public Contact contact;

    @Override
    public Person build()
            throws Exception {
        Person a = new Person();
        a.setMother(mother);
        a.setOwnerGroup(ownerGroup);
        a.setFather(father);
        a.setOwnerUser(ownerUser);
        a.setCategory(category);
        a.setRoleCount(36144224);
        a.setEmployee(true);
        a.setBankCount(141150881);
        a.setGender(Gender.HRTM);
        a.setPronoun("urqcne; owie! dp iq");
        a.setSexualOrientation("yxf*uf iezhoq'lgni upa");
        a.setHomeland("Etuia");
        a.setPassport("oryo oeg ai");
        a.setSsn("Egi. wu, Dsa?");
        a.setDln("u_Eaoioud ne");
        a.setContact(new ContactSamples().build());
        return a;
    }

    @Override
    public PersonSamples wireAny(IRandomPicker picker) {
        this.mother = picker.pickAny(PersonMapper.class, "person");
        this.ownerGroup = picker.pickAny(GroupMapper.class, "group");
        this.father = picker.pickAny(PersonMapper.class, "person");
        this.ownerUser = picker.pickAny(UserMapper.class, "user");
        this.category = picker.pickAny(PartyCategoryMapper.class, "partycat");
        return this;
    }

    @Override
    public Person buildWired(IRandomPicker picker) throws Exception {
        return wireAny(picker).build();
    }

}
