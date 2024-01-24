package net.bodz.lily.schema.contact.dao;

import net.bodz.lily.schema.contact.PersonRole;
import net.bodz.lily.schema.contact.PersonRoleSamples;
import net.bodz.lily.test.AbstractManagerTest;

public class PersonRoleManagerTest
        extends AbstractManagerTest<PersonRole, PersonRoleMapper, PersonRoleManager> {

    @Override
    public PersonRole buildSample()
            throws Exception {
        PersonRoleSamples a = new PersonRoleSamples();
        return a.buildWired(tables);
    }

}
