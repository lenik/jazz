package net.bodz.violet.schema.tran.dao;

import net.bodz.lily.test.AbstractManagerTest;
import net.bodz.violet.schema.tran.TransportPhase;
import net.bodz.violet.schema.tran.TransportPhaseSamples;

public class TransportPhaseManagerTest
        extends AbstractManagerTest<TransportPhase, TransportPhaseMapper, TransportPhaseManager> {

    @Override
    public TransportPhase buildSample()
            throws Exception {
        TransportPhaseSamples a = new TransportPhaseSamples();
        return a.buildWired(tables);
    }

}
