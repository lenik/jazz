package net.bodz.violet.fab.impl;

import net.bodz.lily.test.AbstractTableTest;
import net.bodz.violet.fab.FabTrackTestParameter;
import net.bodz.violet.fab.FabTrackTestParameterSamples;

public class FabTrackTestParameterMapperTest
        extends AbstractTableTest<FabTrackTestParameter, FabTrackTestParameterMask, FabTrackTestParameterMapper> {

    @Override
    public FabTrackTestParameter buildSample() {
        return FabTrackTestParameterSamples.build();
    }

}
