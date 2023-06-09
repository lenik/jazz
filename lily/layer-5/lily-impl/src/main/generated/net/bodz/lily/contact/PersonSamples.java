package net.bodz.lily.contact;

import net.bodz.lily.security.Group;
import net.bodz.lily.security.User;
import net.bodz.lily.test.TestSampleBuilder;

public class PersonSamples
        extends TestSampleBuilder {

    public Group ownerGroup;
    public User ownerUser;
    public PartyCategory category;

    public Contact contact;

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

}
