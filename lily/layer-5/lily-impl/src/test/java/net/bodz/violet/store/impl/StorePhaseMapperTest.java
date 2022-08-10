package net.bodz.violet.store.impl;

import net.bodz.lily.test.AbstractMapperTest;
import net.bodz.violet.store.StorePhase;
import net.bodz.violet.store.StorePhaseSamples;

public class StorePhaseMapperTest
        extends AbstractMapperTest<StorePhase, StorePhaseMask, StorePhaseMapper> {

    @Override
    public StorePhase buildSample() {
        return StorePhaseSamples.build();
    }

}
