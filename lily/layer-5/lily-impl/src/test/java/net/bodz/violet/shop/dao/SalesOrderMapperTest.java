package net.bodz.violet.shop.dao;

import net.bodz.lily.contact.dao.OrganizationMapper;
import net.bodz.lily.contact.dao.PersonMapper;
import net.bodz.lily.schema.dao.FormDefMapper;
import net.bodz.lily.security.dao.GroupMapper;
import net.bodz.lily.security.dao.UserMapper;
import net.bodz.lily.test.AbstractTableTest;
import net.bodz.violet.plan.dao.PlanMapper;
import net.bodz.violet.shop.SalesOrder;
import net.bodz.violet.shop.SalesOrderSamples;

public class SalesOrderMapperTest
        extends AbstractTableTest<SalesOrder, SalesOrderMask, SalesOrderMapper> {

    @Override
    public SalesOrder buildSample()
            throws Exception {
        SalesOrderSamples a = new SalesOrderSamples();
        a.customer = tables.pickAny(PersonMapper.class, "person");
        a.form = tables.pickAny(FormDefMapper.class, "_form");
        a.phase = tables.pickAny(SalesPhaseMapper.class, "salephase");
        a.customerOrg = tables.pickAny(OrganizationMapper.class, "org");
        a.ownerGroup = tables.pickAny(GroupMapper.class, "group");
        a.ownerUser = tables.pickAny(UserMapper.class, "user");
        a.previousOrder = tables.pickAny(SalesOrderMapper.class, "saleodr");
        a.op = tables.pickAny(UserMapper.class, "user");
        a.plan = tables.pickAny(PlanMapper.class, "plan");
        a.category = tables.pickAny(SalesCategoryMapper.class, "salecat");
        return a.build();
    }

}
