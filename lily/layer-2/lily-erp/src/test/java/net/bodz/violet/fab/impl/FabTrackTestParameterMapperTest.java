package net.bodz.violet.fab.impl;

import net.bodz.lily.test.AbstractMapperTest;
import net.bodz.violet.fab.FabTrackTestParameter;
import net.bodz.violet.fab.FabTrackTestParameterSamples;

public class FabTrackTestParameterMapperTest
        extends AbstractMapperTest<FabTrackTestParameter, FabTrackTestParameterMask, FabTrackTestParameterMapper> {

    @Override
    public FabTrackTestParameter buildSample() {
        return FabTrackTestParameterSamples.build();
    }

}
