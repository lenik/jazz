package net.bodz.lily.schema.meta.dao;

import net.bodz.lily.schema.meta.SchemaDef;
import net.bodz.lily.schema.meta.SchemaDefSamples;
import net.bodz.lily.test.AbstractTableTest;

public class SchemaDefMapperTest
        extends AbstractTableTest<SchemaDef, SchemaDefMapper> {

    @Override
    public SchemaDef buildSample()
            throws Exception {
        SchemaDefSamples a = new SchemaDefSamples();
        return a.buildWired(tables);
    }

}
