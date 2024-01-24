package net.bodz.violet.schema.tran.dao;

import net.bodz.lily.test.AbstractTableTest;
import net.bodz.violet.schema.tran.TransportPhase;
import net.bodz.violet.schema.tran.TransportPhaseSamples;

public class TransportPhaseMapperTest
        extends AbstractTableTest<TransportPhase, TransportPhaseMapper> {

    @Override
    public TransportPhase buildSample()
            throws Exception {
        TransportPhaseSamples a = new TransportPhaseSamples();
        return a.buildWired(tables);
    }

}
