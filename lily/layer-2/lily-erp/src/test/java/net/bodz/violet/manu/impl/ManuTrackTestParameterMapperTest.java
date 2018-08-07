package net.bodz.violet.manu.impl;

import net.bodz.bas.db.ctx.DataContext;
import net.bodz.lily.test.AbstractMapperTest;
import net.bodz.violet.VioletTests;
import net.bodz.violet.manu.ManuTrackTestParameter;
import net.bodz.violet.manu.ManuTrackTestParameterSamples;

public class ManuTrackTestParameterMapperTest
        extends AbstractMapperTest<ManuTrackTestParameter, ManuTrackTestParameterMask, ManuTrackTestParameterMapper> {

    @Override
    public DataContext getContext() {
        return VioletTests.getDefaultContext();
    }

    @Override
    public ManuTrackTestParameter buildSample() {
        return ManuTrackTestParameterSamples.build();
    }

}
