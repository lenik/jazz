package net.bodz.violet.tran.impl;

import net.bodz.lily.test.AbstractMapperTest;
import net.bodz.violet.tran.TransportPhase;
import net.bodz.violet.tran.TransportPhaseSamples;

public class TransportPhaseMapperTest
        extends AbstractMapperTest<TransportPhase, TransportPhaseMask, TransportPhaseMapper> {

    @Override
    public TransportPhase buildSample() {
        return TransportPhaseSamples.build();
    }

}
