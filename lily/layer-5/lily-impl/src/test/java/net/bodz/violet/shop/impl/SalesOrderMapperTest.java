package net.bodz.violet.shop.impl;

import net.bodz.bas.db.ctx.DataContext;
import net.bodz.lily.contact.Organization;
import net.bodz.lily.contact.Person;
import net.bodz.lily.contact.impl.OrganizationMapper;
import net.bodz.lily.contact.impl.PersonMapper;
import net.bodz.lily.security.User;
import net.bodz.lily.security.impl.UserMapper;
import net.bodz.lily.test.AbstractMapperTest;
import net.bodz.violet.TestData;
import net.bodz.violet.plan.Plan;
import net.bodz.violet.plan.impl.PlanMapper;
import net.bodz.violet.shop.SalesCategory;
import net.bodz.violet.shop.SalesOrder;
import net.bodz.violet.shop.SalesOrderSamples;
import net.bodz.violet.shop.SalesPhase;

public class SalesOrderMapperTest
        extends AbstractMapperTest<SalesOrder, SalesOrderMask, SalesOrderMapper> {

    @Override
    public DataContext getContext() {
        return TestData.getDefaultContext();
    }

    @Override
    public SalesOrder buildSample() {
        User op = tables.pickAny(UserMapper.class, "user");
        SalesCategory category = tables.pickAny(SalesCategoryMapper.class, "salecat");
        SalesPhase phase = tables.pickAny(SalesPhaseMapper.class, "salephase");
        SalesOrder previousOrder = tables.pickAny(SalesOrderMapper.class, "saleodr");
        Plan plan = tables.pickAny(PlanMapper.class, "plan");
        Organization org = tables.pickAny(OrganizationMapper.class, "org");
        Person person = tables.pickAny(PersonMapper.class, "person");
        return SalesOrderSamples.build(op, category, phase, previousOrder, plan, org, person);
    }

}
