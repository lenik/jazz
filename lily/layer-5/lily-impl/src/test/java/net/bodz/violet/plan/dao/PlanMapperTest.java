package net.bodz.violet.plan.dao;

import net.bodz.lily.schema.dao.FormDefMapper;
import net.bodz.lily.security.dao.GroupMapper;
import net.bodz.lily.security.dao.UserMapper;
import net.bodz.lily.test.AbstractTableTest;
import net.bodz.violet.plan.Plan;
import net.bodz.violet.plan.PlanSamples;

public class PlanMapperTest
        extends AbstractTableTest<Plan, PlanMask, PlanMapper> {

    @Override
    public Plan buildSample()
            throws Exception {
        PlanSamples a = new PlanSamples();
        a.form = tables.pickAny(FormDefMapper.class, "_form");
        a.category = tables.pickAny(PlanCategoryMapper.class, "plancat");
        a.ownerUser = tables.pickAny(UserMapper.class, "user");
        a.ownerGroup = tables.pickAny(GroupMapper.class, "group");
        a.phase = tables.pickAny(PlanPhaseMapper.class, "planphase");
        a.op = tables.pickAny(UserMapper.class, "user");
        return a.build();
    }

}
