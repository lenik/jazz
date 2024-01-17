package net.bodz.violet.plan.dao;

import net.bodz.lily.schema.dao.FormDefMapper;
import net.bodz.lily.security.dao.GroupMapper;
import net.bodz.lily.security.dao.UserMapper;
import net.bodz.lily.test.AbstractTableTest;
import net.bodz.violet.plan.PlanDo;
import net.bodz.violet.plan.PlanDoSamples;

public class PlanDoMapperTest
        extends AbstractTableTest<PlanDo, PlanDoCriteriaBuilder, PlanDoMapper> {

    @Override
    public PlanDo buildSample()
            throws Exception {
        PlanDoSamples a = new PlanDoSamples();
        a.op = tables.pickAny(UserMapper.class, "user");
        a.ownerGroup = tables.pickAny(GroupMapper.class, "group");
        a.form = tables.pickAny(FormDefMapper.class, "_form");
        a.plan = tables.pickAny(PlanMapper.class, "plan");
        a.ownerUser = tables.pickAny(UserMapper.class, "user");
        a.parent = tables.pickAny(PlanDoMapper.class, "plando");
        return a.build();
    }

}
