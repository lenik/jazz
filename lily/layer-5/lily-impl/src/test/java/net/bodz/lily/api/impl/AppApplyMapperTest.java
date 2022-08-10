package net.bodz.lily.api.impl;

import net.bodz.lily.api.AppApply;
import net.bodz.lily.api.AppApplySamples;
import net.bodz.lily.test.AbstractMapperTest;

public class AppApplyMapperTest
        extends AbstractMapperTest<AppApply, AppApplyMask, AppApplyMapper> {

    @Override
    public AppApply buildSample() {
        return AppApplySamples.build();
    }

}
