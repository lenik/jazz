package net.bodz.lily.schema.impl;

import net.bodz.lily.schema.PhaseDef;
import net.bodz.lily.schema.PhaseDefSamples;
import net.bodz.lily.schema.SchemaDef;
import net.bodz.lily.test.AbstractMapperTest;

public class PhaseDefMapperTest
        extends AbstractMapperTest<PhaseDef, PhaseDefMask, PhaseDefMapper> {

    @Override
    public PhaseDef buildSample() {
        SchemaDef schema = tables.pickAny(SchemaDefMapper.class, "_schema");
        return PhaseDefSamples.build(schema);
    }

}
