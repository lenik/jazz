package net.bodz.violet.store.dao;

import net.bodz.lily.security.dao.GroupMapper;
import net.bodz.lily.security.dao.UserMapper;
import net.bodz.lily.test.AbstractTableTest;
import net.bodz.violet.store.StorePhase;
import net.bodz.violet.store.StorePhaseSamples;

public class StorePhaseMapperTest
        extends AbstractTableTest<StorePhase, StorePhaseMask, StorePhaseMapper> {

    @Override
    public StorePhase buildSample()
            throws Exception {
        StorePhaseSamples a = new StorePhaseSamples();
        a.ownerUser = tables.pickAny(UserMapper.class, "user");
        a.ownerGroup = tables.pickAny(GroupMapper.class, "group");
        return a.build();
    }

}
