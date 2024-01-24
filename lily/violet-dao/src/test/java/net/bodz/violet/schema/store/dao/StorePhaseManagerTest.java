package net.bodz.violet.schema.store.dao;

import net.bodz.lily.test.AbstractManagerTest;
import net.bodz.violet.schema.store.StorePhase;
import net.bodz.violet.schema.store.StorePhaseSamples;

public class StorePhaseManagerTest
        extends AbstractManagerTest<StorePhase, StorePhaseMapper, StorePhaseManager> {

    @Override
    public StorePhase buildSample()
            throws Exception {
        StorePhaseSamples a = new StorePhaseSamples();
        return a.buildWired(tables);
    }

}
