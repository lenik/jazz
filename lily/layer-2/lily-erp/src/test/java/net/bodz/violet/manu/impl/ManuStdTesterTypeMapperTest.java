package net.bodz.violet.manu.impl;

import net.bodz.bas.db.ctx.DataContext;
import net.bodz.lily.test.AbstractMapperTest;
import net.bodz.violet.VioletTests;
import net.bodz.violet.manu.ManuStdTesterType;
import net.bodz.violet.manu.ManuStdTesterTypeSamples;

public class ManuStdTesterTypeMapperTest
        extends AbstractMapperTest<ManuStdTesterType, ManuStdTesterTypeMask, ManuStdTesterTypeMapper> {

    @Override
    public DataContext getContext() {
        return VioletTests.getDefaultContext();
    }

    @Override
    public ManuStdTesterType buildSample() {
        return ManuStdTesterTypeSamples.build();
    }

}
