package net.bodz.violet.store.dao;

import net.bodz.lily.test.AbstractManagerTest;
import net.bodz.violet.store.StorePhase;
import net.bodz.violet.store.StorePhaseSamples;

public class StorePhaseManagerTest
        extends AbstractManagerTest<StorePhase, StorePhaseMapper, StorePhaseManager> {

    @Override
    public StorePhase buildSample()
            throws Exception {
        StorePhaseSamples a = new StorePhaseSamples();
        return a.buildWired(tables);
    }

}
