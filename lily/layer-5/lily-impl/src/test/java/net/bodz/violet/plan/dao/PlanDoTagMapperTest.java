package net.bodz.violet.plan.dao;

import net.bodz.lily.security.dao.GroupMapper;
import net.bodz.lily.security.dao.UserMapper;
import net.bodz.lily.test.AbstractTableTest;
import net.bodz.violet.plan.PlanDoTag;
import net.bodz.violet.plan.PlanDoTagSamples;

public class PlanDoTagMapperTest
        extends AbstractTableTest<PlanDoTag, PlanDoTagMask, PlanDoTagMapper> {

    @Override
    public PlanDoTag buildSample()
            throws Exception {
        PlanDoTagSamples a = new PlanDoTagSamples();
        a.ownerGroup = tables.pickAny(GroupMapper.class, "group");
        a.parent = tables.pickAny(PlanDoTagMapper.class, "plandotag");
        a.ownerUser = tables.pickAny(UserMapper.class, "user");
        return a.build();
    }

}
