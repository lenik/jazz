package net.bodz.lily.schema.dao;

import net.bodz.lily.schema.FormDef;
import net.bodz.lily.schema.FormDefSamples;
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
