package net.bodz.lily.schema.contact.dao;

import net.bodz.lily.schema.contact.PersonTag;
import net.bodz.lily.schema.contact.PersonTagSamples;
import net.bodz.lily.test.AbstractManagerTest;

public class PersonTagManagerTest
        extends AbstractManagerTest<PersonTag, PersonTagMapper, PersonTagManager> {

    @Override
    public PersonTag buildSample()
            throws Exception {
        PersonTagSamples a = new PersonTagSamples();
        return a.buildWired(tables);
    }

}
