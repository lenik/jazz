package net.bodz.violet.tran.dao;

import net.bodz.lily.test.AbstractTableTest;
import net.bodz.violet.tran.TransportPhase;
import net.bodz.violet.tran.TransportPhaseSamples;

public class TransportPhaseMapperTest
        extends AbstractTableTest<TransportPhase, TransportPhaseMapper> {

    @Override
    public TransportPhase buildSample()
            throws Exception {
        TransportPhaseSamples a = new TransportPhaseSamples();
        return a.buildWired(tables);
    }

}
