package net.bodz.lily.api.impl;

import net.bodz.lily.api.AppApply;
import net.bodz.lily.api.AppApplySamples;
import net.bodz.lily.test.AbstractTableTest;

public class AppApplyMapperTest
        extends AbstractTableTest<AppApply, AppApplyMask, AppApplyMapper> {

    @Override
    public AppApply buildSample() {
        return AppApplySamples.build();
    }

}
