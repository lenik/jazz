package net.bodz.lily.schema.contact;

import net.bodz.lily.schema.contact.dao.OrgUnitMapper;
import net.bodz.lily.schema.contact.dao.OrganizationMapper;
import net.bodz.lily.schema.contact.dao.PersonMapper;
import net.bodz.lily.test.TestSampleBuilder;
import net.bodz.lily.util.IRandomPicker;

public class PersonRoleSamples
        extends TestSampleBuilder {

    public OrgUnit orgUnit;
    public Person person;
    public Organization org;

    @Override
    public PersonRole build()
            throws Exception {
        PersonRole a = new PersonRole();
        a.setOrgUnit(orgUnit);
        a.setPerson(person);
        a.setOrg(org);
        a.setRole("ai?");
        return a;
    }

    @Override
    public PersonRoleSamples wireAny(IRandomPicker picker) {
        this.orgUnit = picker.pickAny(OrgUnitMapper.class, "orgunit");
        this.person = picker.pickAny(PersonMapper.class, "person");
        this.org = picker.pickAny(OrganizationMapper.class, "org");
        return this;
    }

    @Override
    public PersonRole buildWired(IRandomPicker picker) throws Exception {
        return wireAny(picker).build();
    }

}
