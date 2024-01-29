package net.bodz.lily.schema.contact.dao;

import net.bodz.lily.schema.contact.PersonTag;
import net.bodz.lily.schema.contact.PersonTagSamples;
import net.bodz.lily.test.AbstractTableTest;

public class PersonTagMapperTest
        extends AbstractTableTest<PersonTag, PersonTagMapper> {

    @Override
    public PersonTag buildSample()
            throws Exception {
        PersonTagSamples a = new PersonTagSamples();
        return a.buildWired(tables);
    }

}
