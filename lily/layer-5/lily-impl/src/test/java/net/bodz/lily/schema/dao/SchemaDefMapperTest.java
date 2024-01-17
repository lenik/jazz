package net.bodz.lily.schema.dao;

import net.bodz.lily.schema.SchemaDef;
import net.bodz.lily.schema.SchemaDefSamples;
import net.bodz.lily.test.AbstractTableTest;

public class SchemaDefMapperTest
        extends AbstractTableTest<SchemaDef, SchemaDefCriteriaBuilder, SchemaDefMapper> {

    @Override
    public SchemaDef buildSample()
            throws Exception {
        SchemaDefSamples a = new SchemaDefSamples();
        return a.build();
    }

}
