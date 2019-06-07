package net.bodz.violet.plan;

import net.bodz.lily.contact.Organization;
import net.bodz.lily.contact.Person;
import net.bodz.lily.test.TestSamples;

public class PlanPartySamples
        extends TestSamples {

    public static PlanParty build(Plan plan, Person person, Organization org) {
        PlanParty a = new PlanParty();
        a.setDescription("A planParty named planParty-1.");
        a.setPlan(plan);
        switch (random.nextInt(2)) {
        case 0:
            a.setPerson(person);
            break;
        case 1:
            a.setOrg(org);
            break;
        }
        return a;
    }

}
