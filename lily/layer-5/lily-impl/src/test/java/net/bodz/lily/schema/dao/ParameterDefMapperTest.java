package net.bodz.lily.schema.dao;

import net.bodz.lily.schema.ParameterDef;
import net.bodz.lily.schema.ParameterDefSamples;
import net.bodz.lily.test.AbstractTableTest;

public class ParameterDefMapperTest
        extends AbstractTableTest<ParameterDef, ParameterDefMask, ParameterDefMapper> {

    @Override
    public ParameterDef buildSample()
            throws Exception {
        ParameterDefSamples a = new ParameterDefSamples();
        a.schema = tables.pickAny(SchemaDefMapper.class, "_schema");
        return a.build();
    }

}
