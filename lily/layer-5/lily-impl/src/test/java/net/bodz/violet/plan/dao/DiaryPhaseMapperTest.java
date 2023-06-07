package net.bodz.violet.plan.dao;

import net.bodz.lily.security.dao.GroupMapper;
import net.bodz.lily.security.dao.UserMapper;
import net.bodz.lily.test.AbstractTableTest;
import net.bodz.violet.plan.DiaryPhase;
import net.bodz.violet.plan.DiaryPhaseSamples;

public class DiaryPhaseMapperTest
        extends AbstractTableTest<DiaryPhase, DiaryPhaseMask, DiaryPhaseMapper> {

    @Override
    public DiaryPhase buildSample()
            throws Exception {
        DiaryPhaseSamples a = new DiaryPhaseSamples();
        a.ownerUser = tables.pickAny(UserMapper.class, "user");
        a.ownerGroup = tables.pickAny(GroupMapper.class, "group");
        return a.build();
    }

}
