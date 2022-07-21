package net.bodz.lily.schema.impl;

import net.bodz.bas.db.ctx.DataContext;
import net.bodz.lily.schema.SchemaDef;
import net.bodz.lily.schema.SchemaDefSamples;
import net.bodz.lily.test.AbstractMapperTest;
import net.bodz.violet.TestData;

public class SchemaDefMapperTest
        extends AbstractMapperTest<SchemaDef, SchemaDefMask, SchemaDefMapper> {

    @Override
    public DataContext getContext() {
        return TestData.getDefaultContext();
    }

    @Override
    public SchemaDef buildSample() {
        return SchemaDefSamples.build();
    }

}
