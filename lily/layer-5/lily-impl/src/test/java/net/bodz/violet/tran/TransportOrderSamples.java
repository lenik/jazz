package net.bodz.violet.tran;

import net.bodz.lily.contact.OrgUnit;
import net.bodz.lily.contact.Organization;
import net.bodz.lily.contact.Person;
import net.bodz.lily.security.User;
import net.bodz.lily.test.TestSamples;
import net.bodz.violet.shop.SalesOrder;
import net.bodz.violet.store.StoreOrder;

public class TransportOrderSamples
        extends TestSamples {

    public static TransportOrder build(User op, TransportCategory category, TransportPhase phase,
            TransportOrder previousOrder, //
            SalesOrder salesOrder, StoreOrder storeOrder, //
            Organization org, OrgUnit orgUnit, Person person, //
            Organization shipper) {
        TransportOrder a = new TransportOrder();
        a.setSubject("transportOrder-1");
        a.setText("A transportOrder named transportOrder-1.");

        a.setOp(op);

        a.setCategory(category);
        a.setPhase(phase);
        if (random.nextInt(100) < 30)
            a.setPreviousOrder(previousOrder);

        a.setSalesOrder(salesOrder);
        a.setStoreOrder(storeOrder);

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

        a.setShipper(shipper);
        a.setShippingCost(random.nextInt(10000) / 100.0);
        return a;
    }

}
