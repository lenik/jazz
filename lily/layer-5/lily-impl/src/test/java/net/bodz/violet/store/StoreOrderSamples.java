package net.bodz.violet.store;

import net.bodz.lily.contact.OrgUnit;
import net.bodz.lily.contact.Organization;
import net.bodz.lily.contact.Person;
import net.bodz.lily.security.User;
import net.bodz.lily.test.TestSamples;
import net.bodz.violet.plan.Plan;

public class StoreOrderSamples
        extends TestSamples {

    public static StoreOrder build(User op, StoreCategory category, StorePhase phase, Plan plan, Organization org,
            OrgUnit orgUnit, Person person) {
        StoreOrder a = new StoreOrder();
        a.setSubject("storeOrder-1");
        a.setRawText("A storeOrder named storeOrder-1.");
        a.setOp(op);

        a.setCategory(category);
        a.setPhase(phase);
        a.setPlan(plan);

        switch (random.nextInt(3)) {
        case 0:
            a.setOrg(org);
            break;
        case 1:
            a.setOrgUnit(orgUnit);
            break;
        case 2:
            a.setPerson(person);
            break;
        }
        return a;
    }

}
