package net.bodz.lily.contact.impl;

import net.bodz.lily.contact.Person;
import net.bodz.lily.contact.PersonSamples;
import net.bodz.lily.test.AbstractMapperTest;

public class PersonMapperTest
        extends AbstractMapperTest<Person, PersonMask, PersonMapper> {

    @Override
    public Person buildSample() {
        return PersonSamples.build();
    }

}
