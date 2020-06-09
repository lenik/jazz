package net.bodz.violet.fab.impl;

import net.bodz.bas.db.ctx.DataContext;
import net.bodz.lily.test.AbstractMapperTest;
import net.bodz.violet.VioletTests;
import net.bodz.violet.fab.FabTrackTestParameter;
import net.bodz.violet.fab.FabTrackTestParameterSamples;

public class FabTrackTestParameterMapperTest
        extends AbstractMapperTest<FabTrackTestParameter, FabTrackTestParameterMask, FabTrackTestParameterMapper> {

    @Override
    public DataContext getContext() {
        return VioletTests.getDefaultContext();
    }

    @Override
    public FabTrackTestParameter buildSample() {
        return FabTrackTestParameterSamples.build();
    }

}
