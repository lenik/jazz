package net.bodz.lily.schema.meta.dao;

import net.bodz.lily.schema.meta.PhaseDef;
import net.bodz.lily.schema.meta.PhaseDefSamples;
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
