package net.bodz.lily.schema.impl;

import net.bodz.bas.db.ctx.DataContext;
import net.bodz.lily.schema.FormDef;
import net.bodz.lily.schema.FormDefSamples;
import net.bodz.lily.test.AbstractMapperTest;
import net.bodz.violet.VioletTests;

public class FormDefMapperTest
        extends AbstractMapperTest<FormDef, FormDefMask, FormDefMapper> {

    @Override
    public DataContext getContext() {
        return VioletTests.getDefaultContext();
    }

    @Override
    public FormDef buildSample() {
        return FormDefSamples.build();
    }

}
