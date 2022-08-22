package net.bodz.lily.schema.impl;

import net.bodz.lily.schema.PhaseDef;
import net.bodz.lily.schema.PhaseDefSamples;
import net.bodz.lily.schema.SchemaDef;
import net.bodz.lily.test.AbstractTableTest;

public class PhaseDefMapperTest
        extends AbstractTableTest<PhaseDef, PhaseDefMask, PhaseDefMapper> {

    @Override
    public PhaseDef buildSample() {
        SchemaDef schema = tables.pickAny(SchemaDefMapper.class, "_schema");
        return PhaseDefSamples.build(schema);
    }

}
