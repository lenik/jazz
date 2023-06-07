package net.bodz.violet.store.dao;

import net.bodz.lily.contact.dao.OrgUnitMapper;
import net.bodz.lily.contact.dao.OrganizationMapper;
import net.bodz.lily.contact.dao.PersonMapper;
import net.bodz.lily.schema.dao.FormDefMapper;
import net.bodz.lily.security.dao.GroupMapper;
import net.bodz.lily.security.dao.UserMapper;
import net.bodz.lily.test.AbstractTableTest;
import net.bodz.violet.plan.dao.PlanMapper;
import net.bodz.violet.store.StoreOrder;
import net.bodz.violet.store.StoreOrderSamples;

public class StoreOrderMapperTest
        extends AbstractTableTest<StoreOrder, StoreOrderMask, StoreOrderMapper> {

    @Override
    public StoreOrder buildSample()
            throws Exception {
        StoreOrderSamples a = new StoreOrderSamples();
        a.person = tables.pickAny(PersonMapper.class, "person");
        a.plan = tables.pickAny(PlanMapper.class, "plan");
        a.ownerGroup = tables.pickAny(GroupMapper.class, "group");
        a.phase = tables.pickAny(StorePhaseMapper.class, "storephase");
        a.category = tables.pickAny(StoreCategoryMapper.class, "storecat");
        a.ownerUser = tables.pickAny(UserMapper.class, "user");
        a.prev = tables.pickAny(StoreOrderMapper.class, "storeodr");
        a.op = tables.pickAny(UserMapper.class, "user");
        a.form = tables.pickAny(FormDefMapper.class, "_form");
        a.org = tables.pickAny(OrganizationMapper.class, "org");
        a.orgUnit = tables.pickAny(OrgUnitMapper.class, "orgunit");
        return a.build();
    }

}
