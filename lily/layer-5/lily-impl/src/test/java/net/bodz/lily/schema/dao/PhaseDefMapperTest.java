package net.bodz.lily.schema.dao;

import net.bodz.lily.schema.PhaseDef;
import net.bodz.lily.schema.PhaseDefSamples;
import net.bodz.lily.test.AbstractTableTest;

public class PhaseDefMapperTest
        extends AbstractTableTest<PhaseDef, PhaseDefCriteriaBuilder, PhaseDefMapper> {

    @Override
    public PhaseDef buildSample()
            throws Exception {
        PhaseDefSamples a = new PhaseDefSamples();
        a.schema = tables.pickAny(SchemaDefMapper.class, "_schema");
        return a.build();
    }

}
