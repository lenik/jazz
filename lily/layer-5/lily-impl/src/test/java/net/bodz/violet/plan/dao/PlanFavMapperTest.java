package net.bodz.violet.plan.dao;

import net.bodz.lily.security.dao.UserMapper;
import net.bodz.lily.test.AbstractTableTest;
import net.bodz.violet.plan.PlanFav;
import net.bodz.violet.plan.PlanFavSamples;

public class PlanFavMapperTest
        extends AbstractTableTest<PlanFav, PlanFavMask, PlanFavMapper> {

    @Override
    public PlanFav buildSample()
            throws Exception {
        PlanFavSamples a = new PlanFavSamples();
        a.plan = tables.pickAny(PlanMapper.class, "plan");
        a.user = tables.pickAny(UserMapper.class, "user");
        return a.build();
    }

}
