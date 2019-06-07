package net.bodz.violet.tran.impl;

import net.bodz.bas.db.ctx.DataContext;
import net.bodz.lily.contact.OrgUnit;
import net.bodz.lily.contact.Organization;
import net.bodz.lily.contact.Person;
import net.bodz.lily.contact.impl.OrgUnitMapper;
import net.bodz.lily.contact.impl.OrganizationMapper;
import net.bodz.lily.contact.impl.PersonMapper;
import net.bodz.lily.security.User;
import net.bodz.lily.security.impl.UserMapper;
import net.bodz.lily.test.AbstractMapperTest;
import net.bodz.violet.VioletTests;
import net.bodz.violet.shop.SalesOrder;
import net.bodz.violet.shop.impl.SalesOrderMapper;
import net.bodz.violet.store.StoreOrder;
import net.bodz.violet.store.impl.StoreOrderMapper;
import net.bodz.violet.tran.TransportCategory;
import net.bodz.violet.tran.TransportOrder;
import net.bodz.violet.tran.TransportOrderSamples;
import net.bodz.violet.tran.TransportPhase;

public class TransportOrderMapperTest
        extends AbstractMapperTest<TransportOrder, TransportOrderMask, TransportOrderMapper> {

    @Override
    public DataContext getContext() {
        return VioletTests.getDefaultContext();
    }

    @Override
    public TransportOrder buildSample() {
        User op = tables.pickAny(UserMapper.class, "user");
        TransportCategory category = tables.pickAny(TransportCategoryMapper.class, "trancat");
        TransportPhase phase = tables.pickAny(TransportPhaseMapper.class, "tranphase");
        TransportOrder previousOrder = tables.pickAny(TransportOrderMapper.class, "tranodr");
        SalesOrder salesOrder = tables.pickAny(SalesOrderMapper.class, "saleodr");
        StoreOrder storeOrder = tables.pickAny(StoreOrderMapper.class, "storeodr");
        Organization org = tables.pickAny(OrganizationMapper.class, "org");
        OrgUnit orgUnit = tables.pickAny(OrgUnitMapper.class, "orgunit");
        Person person = tables.pickAny(PersonMapper.class, "person");
        Organization shipper = tables.pickAny(OrganizationMapper.class, "org");
        return TransportOrderSamples.build(op, category, phase, previousOrder, salesOrder, storeOrder, org, orgUnit,
                person, shipper);
    }

}
