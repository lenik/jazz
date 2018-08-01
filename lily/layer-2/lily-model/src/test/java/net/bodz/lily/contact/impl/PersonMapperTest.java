package net.bodz.lily.contact.impl;

import net.bodz.bas.db.ctx.DataContext;
import net.bodz.lily.contact.Person;
import net.bodz.lily.contact.PersonSamples;
import net.bodz.lily.test.AbstractMapperTest;
import net.bodz.violet.VioletTests;

public class PersonMapperTest
        extends AbstractMapperTest<Person, PersonMask, PersonMapper> {

    @Override
    public DataContext getContext() {
        return VioletTests.getDefaultContext();
    }

    @Override
    public Person buildSample() {
        return PersonSamples.build();
    }

}
