package net.bodz.violet.plan;

import net.bodz.lily.contact.Organization;
import net.bodz.lily.contact.Person;
import net.bodz.lily.test.TestSampleBuilder;

public class PlanPartySamples
        extends TestSampleBuilder {

    public Person person;
    public Plan plan;
    public Organization org;

    public PlanParty build()
            throws Exception {
        PlanParty a = new PlanParty();
        a.setPerson(person);
        a.setPlan(plan);
        a.setOrg(org);
        a.setId(7347353080174787957L);
        return a;
    }

}
