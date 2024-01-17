package net.bodz.lily.schema.dao;

import net.bodz.lily.schema.FormDef;
import net.bodz.lily.schema.FormDefSamples;
import net.bodz.lily.test.AbstractTableTest;

public class FormDefMapperTest
        extends AbstractTableTest<FormDef, FormDefMapper> {

    @Override
    public FormDef buildSample()
            throws Exception {
        FormDefSamples a = new FormDefSamples();
        return a.buildWired(tables);
    }

}
