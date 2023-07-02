package net.bodz.lily.schema.dao;

import net.bodz.lily.schema.ParameterValue;
import net.bodz.lily.schema.ParameterValueSamples;
import net.bodz.lily.test.AbstractTableTest;

public class ParameterValueMapperTest
        extends AbstractTableTest<ParameterValue, ParameterValueMask, ParameterValueMapper> {

    @Override
    public ParameterValue buildSample()
            throws Exception {
        ParameterValueSamples a = new ParameterValueSamples();
        a.parameter = tables.pickAny(ParameterDefMapper.class, "_parm");
        return a.build();
    }

}