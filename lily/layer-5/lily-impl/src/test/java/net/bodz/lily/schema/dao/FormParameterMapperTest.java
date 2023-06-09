package net.bodz.lily.schema.dao;

import net.bodz.lily.schema.FormParameter;
import net.bodz.lily.schema.FormParameterSamples;
import net.bodz.lily.test.AbstractTableTest;

public class FormParameterMapperTest
        extends AbstractTableTest<FormParameter, FormParameterMask, FormParameterMapper> {

    @Override
    public FormParameter buildSample()
            throws Exception {
        FormParameterSamples a = new FormParameterSamples();
        a.form = tables.pickAny(FormDefMapper.class, "_form");
        return a.build();
    }

}
