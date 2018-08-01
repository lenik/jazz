package net.bodz.lily.schema.impl;

import net.bodz.bas.db.ctx.DataContext;
import net.bodz.lily.schema.PriorityDef;
import net.bodz.lily.schema.PriorityDefSamples;
import net.bodz.lily.test.AbstractMapperTest;
import net.bodz.violet.VioletTests;

public class PriorityDefMapperTest
        extends AbstractMapperTest<PriorityDef, PriorityDefMask, PriorityDefMapper> {

    @Override
    public DataContext getContext() {
        return VioletTests.getDefaultContext();
    }

    @Override
    public PriorityDef buildSample() {
        return PriorityDefSamples.build();
    }

}
