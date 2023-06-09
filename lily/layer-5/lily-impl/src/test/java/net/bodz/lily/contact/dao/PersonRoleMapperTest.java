package net.bodz.lily.contact.dao;

import net.bodz.lily.contact.PersonRole;
import net.bodz.lily.contact.PersonRoleSamples;
import net.bodz.lily.test.AbstractTableTest;

public class PersonRoleMapperTest
        extends AbstractTableTest<PersonRole, PersonRoleMask, PersonRoleMapper> {

    @Override
    public PersonRole buildSample()
            throws Exception {
        PersonRoleSamples a = new PersonRoleSamples();
        a.orgUnit = tables.pickAny(OrgUnitMapper.class, "orgunit");
        a.person = tables.pickAny(PersonMapper.class, "person");
        a.org = tables.pickAny(OrganizationMapper.class, "org");
        return a.build();
    }

}
