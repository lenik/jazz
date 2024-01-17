package net.bodz.lily.contact.dao;

import net.bodz.lily.contact.Person;
import net.bodz.lily.contact.PersonSamples;
import net.bodz.lily.test.AbstractManagerTest;

public class PersonManagerTest
        extends AbstractManagerTest<Person, PersonMapper, PersonManager> {

    @Override
    public Person buildSample()
            throws Exception {
        PersonSamples a = new PersonSamples();
        return a.buildWired(tables);
    }

}
