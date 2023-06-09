package net.bodz.lily.contact;

import net.bodz.lily.test.TestSampleBuilder;

public class PersonRoleSamples
        extends TestSampleBuilder {

    public OrgUnit orgUnit;
    public Person person;
    public Organization org;

    public PersonRole build()
            throws Exception {
        PersonRole a = new PersonRole();
        a.setOrgUnit(orgUnit);
        a.setPerson(person);
        a.setOrg(org);
        a.setId(596454480);
        a.setRole("ai?");
        return a;
    }

}
