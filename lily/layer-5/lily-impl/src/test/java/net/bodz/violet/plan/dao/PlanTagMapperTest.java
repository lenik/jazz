package net.bodz.violet.plan.dao;

import net.bodz.lily.security.dao.GroupMapper;
import net.bodz.lily.security.dao.UserMapper;
import net.bodz.lily.test.AbstractTableTest;
import net.bodz.violet.plan.PlanTag;
import net.bodz.violet.plan.PlanTagSamples;

public class PlanTagMapperTest
        extends AbstractTableTest<PlanTag, PlanTagMask, PlanTagMapper> {

    @Override
    public PlanTag buildSample()
            throws Exception {
        PlanTagSamples a = new PlanTagSamples();
        a.ownerUser = tables.pickAny(UserMapper.class, "user");
        a.ownerGroup = tables.pickAny(GroupMapper.class, "group");
        a.parent = tables.pickAny(PlanTagMapper.class, "plantag");
        return a.build();
    }

}
