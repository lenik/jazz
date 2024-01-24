package net.bodz.lily.schema.contact.dao;

import net.bodz.lily.schema.contact.PersonRole;
import net.bodz.lily.schema.contact.PersonRoleSamples;
import net.bodz.lily.test.AbstractTableTest;

public class PersonRoleMapperTest
        extends AbstractTableTest<PersonRole, PersonRoleMapper> {

    @Override
    public PersonRole buildSample()
            throws Exception {
        PersonRoleSamples a = new PersonRoleSamples();
        return a.buildWired(tables);
    }

}
