package net.bodz.lily.schema.impl;

import net.bodz.lily.schema.ParameterDef;
import net.bodz.lily.schema.ParameterDefSamples;
import net.bodz.lily.schema.SchemaDef;
import net.bodz.lily.test.AbstractTableTest;

public class ParameterDefMapperTest
        extends AbstractTableTest<ParameterDef, ParameterDefMask, ParameterDefMapper> {

    @Override
    public ParameterDef buildSample() {
        SchemaDef schema = tables.pickAny(SchemaDefMapper.class, "_schema");
        return ParameterDefSamples.build(schema);
    }

}
