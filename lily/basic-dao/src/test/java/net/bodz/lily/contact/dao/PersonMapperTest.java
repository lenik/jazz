package net.bodz.lily.contact.dao;

import net.bodz.lily.contact.Person;
import net.bodz.lily.contact.PersonSamples;
import net.bodz.lily.test.AbstractTableTest;

public class PersonMapperTest
        extends AbstractTableTest<Person, PersonMapper> {

    @Override
    public Person buildSample()
            throws Exception {
        PersonSamples a = new PersonSamples();
        return a.buildWired(tables);
    }

}
