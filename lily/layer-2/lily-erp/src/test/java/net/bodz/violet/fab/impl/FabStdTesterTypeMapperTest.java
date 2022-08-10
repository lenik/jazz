package net.bodz.violet.fab.impl;

import net.bodz.lily.test.AbstractMapperTest;
import net.bodz.violet.fab.FabStdTesterType;
import net.bodz.violet.fab.FabStdTesterTypeSamples;

public class FabStdTesterTypeMapperTest
        extends AbstractMapperTest<FabStdTesterType, FabStdTesterTypeMask, FabStdTesterTypeMapper> {

    @Override
    public FabStdTesterType buildSample() {
        return FabStdTesterTypeSamples.build();
    }

}
