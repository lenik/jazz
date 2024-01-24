package net.bodz.lily.schema.meta.dao;

import net.bodz.lily.schema.meta.FormDef;
import net.bodz.lily.schema.meta.FormDefSamples;
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
