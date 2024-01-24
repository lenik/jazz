package net.bodz.lily.schema.meta.dao;

import net.bodz.lily.schema.meta.FormDef;
import net.bodz.lily.schema.meta.FormDefSamples;
import net.bodz.lily.test.AbstractManagerTest;

public class FormDefManagerTest
        extends AbstractManagerTest<FormDef, FormDefMapper, FormDefManager> {

    @Override
    public FormDef buildSample()
            throws Exception {
        FormDefSamples a = new FormDefSamples();
        return a.buildWired(tables);
    }

}
