package net.bodz.violet.schema.store.dao;

import net.bodz.lily.test.AbstractTableTest;
import net.bodz.violet.schema.store.StorePhase;
import net.bodz.violet.schema.store.StorePhaseSamples;

public class StorePhaseMapperTest
        extends AbstractTableTest<StorePhase, StorePhaseMapper> {

    @Override
    public StorePhase buildSample()
            throws Exception {
        StorePhaseSamples a = new StorePhaseSamples();
        return a.buildWired(tables);
    }

}
