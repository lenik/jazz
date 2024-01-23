package net.bodz.lily.schema.dao;

import net.bodz.lily.schema.ParameterValue;
import net.bodz.lily.schema.ParameterValueSamples;
import net.bodz.lily.test.AbstractManagerTest;

public class ParameterValueManagerTest
        extends AbstractManagerTest<ParameterValue, ParameterValueMapper, ParameterValueManager> {

    @Override
    public ParameterValue buildSample()
            throws Exception {
        ParameterValueSamples a = new ParameterValueSamples();
        return a.buildWired(tables);
    }

}
