package net.bodz.lily.schema.impl;

import net.bodz.bas.db.ctx.DataContext;
import net.bodz.lily.schema.ParameterDef;
import net.bodz.lily.schema.ParameterDefSamples;
import net.bodz.lily.schema.SchemaDef;
import net.bodz.lily.test.AbstractMapperTest;
import net.bodz.violet.TestData;

public class ParameterDefMapperTest
        extends AbstractMapperTest<ParameterDef, ParameterDefMask, ParameterDefMapper> {

    @Override
    public DataContext getContext() {
        return TestData.getDefaultContext();
    }

    @Override
    public ParameterDef buildSample() {
        SchemaDef schema = tables.pickAny(SchemaDefMapper.class, "_schema");
        return ParameterDefSamples.build(schema);
    }

}
