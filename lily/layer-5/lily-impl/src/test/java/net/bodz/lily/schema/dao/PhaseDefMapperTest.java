package net.bodz.lily.schema.dao;

import net.bodz.lily.schema.PhaseDef;
import net.bodz.lily.schema.PhaseDefSamples;
import net.bodz.lily.test.AbstractTableTest;

public class PhaseDefMapperTest
        extends AbstractTableTest<PhaseDef, PhaseDefMapper> {

    @Override
    public PhaseDef buildSample()
            throws Exception {
        PhaseDefSamples a = new PhaseDefSamples();
        return a.buildWired(tables);
    }

}
