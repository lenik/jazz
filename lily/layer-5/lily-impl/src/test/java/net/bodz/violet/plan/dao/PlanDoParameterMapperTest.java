package net.bodz.violet.plan.dao;

import net.bodz.lily.security.dao.GroupMapper;
import net.bodz.lily.security.dao.UserMapper;
import net.bodz.lily.test.AbstractTableTest;
import net.bodz.violet.plan.PlanDoParameter;
import net.bodz.violet.plan.PlanDoParameterSamples;

public class PlanDoParameterMapperTest
        extends AbstractTableTest<PlanDoParameter, PlanDoParameterMask, PlanDoParameterMapper> {

    @Override
    public PlanDoParameter buildSample()
            throws Exception {
        PlanDoParameterSamples a = new PlanDoParameterSamples();
        a.ownerUser = tables.pickAny(UserMapper.class, "user");
        a.ownerGroup = tables.pickAny(GroupMapper.class, "group");
        return a.build();
    }

}
