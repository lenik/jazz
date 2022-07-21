package net.bodz.violet.tran.impl;

import net.bodz.bas.db.ctx.DataContext;
import net.bodz.lily.test.AbstractMapperTest;
import net.bodz.violet.TestData;
import net.bodz.violet.tran.TransportPhase;
import net.bodz.violet.tran.TransportPhaseSamples;

public class TransportPhaseMapperTest
        extends AbstractMapperTest<TransportPhase, TransportPhaseMask, TransportPhaseMapper> {

    @Override
    public DataContext getContext() {
        return TestData.getDefaultContext();
    }

    @Override
    public TransportPhase buildSample() {
        return TransportPhaseSamples.build();
    }

}
