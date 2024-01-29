package net.bodz.lily.schema.contact.dao;

import net.bodz.lily.schema.contact.PersonTagType;
import net.bodz.lily.schema.contact.PersonTagTypeSamples;
import net.bodz.lily.test.AbstractManagerTest;

public class PersonTagTypeManagerTest
        extends AbstractManagerTest<PersonTagType, PersonTagTypeMapper, PersonTagTypeManager> {

    @Override
    public PersonTagType buildSample()
            throws Exception {
        PersonTagTypeSamples a = new PersonTagTypeSamples();
        return a.buildWired(tables);
    }

}
