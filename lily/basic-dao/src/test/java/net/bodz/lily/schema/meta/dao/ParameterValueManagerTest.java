package net.bodz.lily.schema.meta.dao;

import net.bodz.lily.schema.meta.ParameterValue;
import net.bodz.lily.schema.meta.ParameterValueSamples;
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
