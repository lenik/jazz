package net.bodz.violet.store.dao;

import net.bodz.lily.test.AbstractTableTest;
import net.bodz.violet.store.StorePhase;
import net.bodz.violet.store.StorePhaseSamples;

public class StorePhaseMapperTest
        extends AbstractTableTest<StorePhase, StorePhaseMapper> {

    @Override
    public StorePhase buildSample()
            throws Exception {
        StorePhaseSamples a = new StorePhaseSamples();
        return a.buildWired(tables);
    }

}
