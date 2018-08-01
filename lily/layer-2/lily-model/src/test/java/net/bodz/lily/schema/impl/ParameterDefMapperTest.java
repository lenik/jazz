package net.bodz.lily.schema.impl;

import net.bodz.bas.db.ctx.DataContext;
import net.bodz.lily.schema.ParameterDef;
import net.bodz.lily.schema.ParameterDefSamples;
import net.bodz.lily.test.AbstractMapperTest;
import net.bodz.violet.VioletTests;

public class ParameterDefMapperTest
        extends AbstractMapperTest<ParameterDef, ParameterDefMask, ParameterDefMapper> {

    @Override
    public DataContext getContext() {
        return VioletTests.getDefaultContext();
    }

    @Override
    public ParameterDef buildSample() {
        return ParameterDefSamples.build();
    }

}
