package net.bodz.lily.schema.impl;

import net.bodz.lily.schema.SchemaDef;
import net.bodz.lily.schema.SchemaDefSamples;
import net.bodz.lily.test.AbstractTableTest;

public class SchemaDefMapperTest
        extends AbstractTableTest<SchemaDef, SchemaDefMask, SchemaDefMapper> {

    @Override
    public SchemaDef buildSample() {
        return SchemaDefSamples.build();
    }

}
