package net.bodz.lily.schema.meta.dao;

import net.bodz.lily.schema.meta.FormParameter;
import net.bodz.lily.schema.meta.FormParameterSamples;
import net.bodz.lily.test.AbstractTableTest;

public class FormParameterMapperTest
        extends AbstractTableTest<FormParameter, FormParameterMapper> {

    @Override
    public FormParameter buildSample()
            throws Exception {
        FormParameterSamples a = new FormParameterSamples();
        return a.buildWired(tables);
    }

}
