package net.bodz.lily.schema.dao;

import net.bodz.lily.schema.PhaseDef;
import net.bodz.lily.schema.PhaseDefSamples;
import net.bodz.lily.test.AbstractManagerTest;

public class PhaseDefManagerTest
        extends AbstractManagerTest<PhaseDef, PhaseDefMapper, PhaseDefManager> {

    @Override
    public PhaseDef buildSample()
            throws Exception {
        PhaseDefSamples a = new PhaseDefSamples();
        return a.buildWired(tables);
    }

}
