package net.bodz.lily.schema.meta.dao;

import net.bodz.lily.schema.meta.ParameterDef;
import net.bodz.lily.schema.meta.ParameterDefSamples;
import net.bodz.lily.test.AbstractTableTest;

public class ParameterDefMapperTest
        extends AbstractTableTest<ParameterDef, ParameterDefMapper> {

    @Override
    public ParameterDef buildSample()
            throws Exception {
        ParameterDefSamples a = new ParameterDefSamples();
        return a.buildWired(tables);
    }

}
