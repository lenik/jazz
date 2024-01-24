package net.bodz.lily.schema.meta.dao;

import net.bodz.lily.schema.meta.FormParameter;
import net.bodz.lily.schema.meta.FormParameterSamples;
import net.bodz.lily.test.AbstractManagerTest;

public class FormParameterManagerTest
        extends AbstractManagerTest<FormParameter, FormParameterMapper, FormParameterManager> {

    @Override
    public FormParameter buildSample()
            throws Exception {
        FormParameterSamples a = new FormParameterSamples();
        return a.buildWired(tables);
    }

}
