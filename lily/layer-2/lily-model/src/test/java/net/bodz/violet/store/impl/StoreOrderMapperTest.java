package net.bodz.violet.store.impl;

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
import net.bodz.violet.plan.Plan;
import net.bodz.violet.plan.impl.PlanMapper;
import net.bodz.violet.store.StoreCategory;
import net.bodz.violet.store.StoreOrder;
import net.bodz.violet.store.StoreOrderSamples;
import net.bodz.violet.store.StorePhase;

public class StoreOrderMapperTest
        extends AbstractMapperTest<StoreOrder, StoreOrderMask, StoreOrderMapper> {

    @Override
    public DataContext getContext() {
        return VioletTests.getDefaultContext();
    }

    @Override
    public StoreOrder buildSample() {
        User op = tables.pickAny(UserMapper.class, "user");
        StoreCategory category = tables.pickAny(StoreCategoryMapper.class, "storecat");
        StorePhase phase = tables.pickAny(StorePhaseMapper.class, "storephase");
        Plan plan = tables.pickAny(PlanMapper.class, "plan");
        Organization org = tables.pickAny(OrganizationMapper.class, "org");
        OrgUnit orgUnit = tables.pickAny(OrgUnitMapper.class, "orgunit");
        Person person = tables.pickAny(PersonMapper.class, "person");
        return StoreOrderSamples.build(op, category, phase, plan, org, orgUnit, person);
    }

}
