package net.bodz.violet.tran.dao;

import net.bodz.lily.security.dao.GroupMapper;
import net.bodz.lily.security.dao.UserMapper;
import net.bodz.lily.test.AbstractTableTest;
import net.bodz.violet.tran.TransportPhase;
import net.bodz.violet.tran.TransportPhaseSamples;

public class TransportPhaseMapperTest
        extends AbstractTableTest<TransportPhase, TransportPhaseCriteriaBuilder, TransportPhaseMapper> {

    @Override
    public TransportPhase buildSample()
            throws Exception {
        TransportPhaseSamples a = new TransportPhaseSamples();
        a.ownerGroup = tables.pickAny(GroupMapper.class, "group");
        a.ownerUser = tables.pickAny(UserMapper.class, "user");
        return a.build();
    }

}
