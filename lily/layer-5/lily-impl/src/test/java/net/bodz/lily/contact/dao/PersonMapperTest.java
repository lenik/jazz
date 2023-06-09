package net.bodz.lily.contact.dao;

import net.bodz.lily.contact.ContactSamples;
import net.bodz.lily.contact.Person;
import net.bodz.lily.contact.PersonSamples;
import net.bodz.lily.security.dao.GroupMapper;
import net.bodz.lily.security.dao.UserMapper;
import net.bodz.lily.test.AbstractTableTest;

public class PersonMapperTest
        extends AbstractTableTest<Person, PersonMask, PersonMapper> {

    @Override
    public Person buildSample()
            throws Exception {
        PersonSamples a = new PersonSamples();
        a.ownerGroup = tables.pickAny(GroupMapper.class, "group");
        a.ownerUser = tables.pickAny(UserMapper.class, "user");
        a.category = tables.pickAny(PartyCategoryMapper.class, "partycat");
        a.contact = new ContactSamples().build();
        return a.build();
    }

}
