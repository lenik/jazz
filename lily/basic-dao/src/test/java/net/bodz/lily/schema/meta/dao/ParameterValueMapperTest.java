package net.bodz.lily.schema.meta.dao;

import net.bodz.lily.schema.meta.ParameterValue;
import net.bodz.lily.schema.meta.ParameterValueSamples;
import net.bodz.lily.test.AbstractTableTest;

public class ParameterValueMapperTest
        extends AbstractTableTest<ParameterValue, ParameterValueMapper> {

    @Override
    public ParameterValue buildSample()
            throws Exception {
        ParameterValueSamples a = new ParameterValueSamples();
        return a.buildWired(tables);
    }

}
