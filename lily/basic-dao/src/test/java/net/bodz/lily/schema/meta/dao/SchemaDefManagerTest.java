package net.bodz.lily.schema.meta.dao;

import net.bodz.lily.schema.meta.SchemaDef;
import net.bodz.lily.schema.meta.SchemaDefSamples;
import net.bodz.lily.test.AbstractManagerTest;

public class SchemaDefManagerTest
        extends AbstractManagerTest<SchemaDef, SchemaDefMapper, SchemaDefManager> {

    @Override
    public SchemaDef buildSample()
            throws Exception {
        SchemaDefSamples a = new SchemaDefSamples();
        return a.buildWired(tables);
    }

}
