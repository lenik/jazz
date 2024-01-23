package net.bodz.violet.tran.dao;

import net.bodz.lily.test.AbstractManagerTest;
import net.bodz.violet.tran.TransportPhase;
import net.bodz.violet.tran.TransportPhaseSamples;

public class TransportPhaseManagerTest
        extends AbstractManagerTest<TransportPhase, TransportPhaseMapper, TransportPhaseManager> {

    @Override
    public TransportPhase buildSample()
            throws Exception {
        TransportPhaseSamples a = new TransportPhaseSamples();
        return a.buildWired(tables);
    }

}
