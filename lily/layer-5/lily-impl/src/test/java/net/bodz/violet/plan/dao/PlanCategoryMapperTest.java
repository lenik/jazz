package net.bodz.violet.plan.dao;

import net.bodz.lily.security.dao.GroupMapper;
import net.bodz.lily.security.dao.UserMapper;
import net.bodz.lily.test.AbstractTableTest;
import net.bodz.violet.plan.PlanCategory;
import net.bodz.violet.plan.PlanCategorySamples;

public class PlanCategoryMapperTest
        extends AbstractTableTest<PlanCategory, PlanCategoryMask, PlanCategoryMapper> {

    @Override
    public PlanCategory buildSample()
            throws Exception {
        PlanCategorySamples a = new PlanCategorySamples();
        a.parent = tables.pickAny(PlanCategoryMapper.class, "plancat");
        a.ownerUser = tables.pickAny(UserMapper.class, "user");
        a.ownerGroup = tables.pickAny(GroupMapper.class, "group");
        return a.build();
    }

}
