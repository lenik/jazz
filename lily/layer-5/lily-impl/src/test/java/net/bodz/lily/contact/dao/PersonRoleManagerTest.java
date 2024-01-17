package net.bodz.lily.contact.dao;

import net.bodz.lily.contact.PersonRole;
import net.bodz.lily.contact.PersonRoleSamples;
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
