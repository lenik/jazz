package net.bodz.violet.shop;

import net.bodz.lily.contact.Organization;
import net.bodz.lily.contact.Person;
import net.bodz.lily.security.User;
import net.bodz.lily.test.TestSamples;
import net.bodz.violet.plan.Plan;

public class SalesOrderSamples
        extends TestSamples {

    public static SalesOrder build(User op, SalesCategory category, SalesPhase phase, SalesOrder previousOrder,
            Plan plan,//
            Organization org, Person person) {
        SalesOrder a = new SalesOrder();
        a.setSubject("salesOrder-1");
        a.setText("A salesOrder named salesOrder-1.");
        a.setOp(op);

        a.setCategory(category);
        a.setPhase(phase);
        if (random.nextInt(100) < 30)
            a.setPreviousOrder(previousOrder);
        a.setPlan(plan);

        switch (random.nextInt(2)) {
        case 0:
            a.setOrg(org);
            break;
        case 1:
            a.setPerson(person);
            break;
        }
        return a;
    }

}
