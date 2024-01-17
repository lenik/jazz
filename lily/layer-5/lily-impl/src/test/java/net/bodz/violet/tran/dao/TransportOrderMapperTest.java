package net.bodz.violet.tran.dao;

import net.bodz.lily.contact.dao.OrganizationMapper;
import net.bodz.lily.geo.dao.ZoneMapper;
import net.bodz.lily.schema.dao.FormDefMapper;
import net.bodz.lily.security.dao.GroupMapper;
import net.bodz.lily.security.dao.UserMapper;
import net.bodz.lily.test.AbstractTableTest;
import net.bodz.violet.shop.dao.SalesOrderMapper;
import net.bodz.violet.store.dao.StoreOrderMapper;
import net.bodz.violet.tran.TransportOrder;
import net.bodz.violet.tran.TransportOrderSamples;

public class TransportOrderMapperTest
        extends AbstractTableTest<TransportOrder, TransportOrderCriteriaBuilder, TransportOrderMapper> {

    @Override
    public TransportOrder buildSample()
            throws Exception {
        TransportOrderSamples a = new TransportOrderSamples();
        a.op = tables.pickAny(UserMapper.class, "user");
        a.ownerGroup = tables.pickAny(GroupMapper.class, "group");
        a.ownerUser = tables.pickAny(UserMapper.class, "user");
        a.prev = tables.pickAny(TransportOrderMapper.class, "tranodr");
        a.form = tables.pickAny(FormDefMapper.class, "_form");
        a.shipper = tables.pickAny(OrganizationMapper.class, "org");
        a.category = tables.pickAny(TransportCategoryMapper.class, "trancat");
        a.salesOrder = tables.pickAny(SalesOrderMapper.class, "saleodr");
        a.phase = tables.pickAny(TransportPhaseMapper.class, "tranphase");
        a.dZone = tables.pickAny(ZoneMapper.class, "zone");
        a.sZone = tables.pickAny(ZoneMapper.class, "zone");
        a.storeodr = tables.pickAny(StoreOrderMapper.class, "storeodr");
        return a.build();
    }

}
