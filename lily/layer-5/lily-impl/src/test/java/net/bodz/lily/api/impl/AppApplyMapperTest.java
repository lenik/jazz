package net.bodz.lily.api.impl;

import net.bodz.bas.db.ctx.DataContext;
import net.bodz.lily.api.AppApply;
import net.bodz.lily.api.AppApplySamples;
import net.bodz.lily.test.AbstractMapperTest;
import net.bodz.violet.TestData;

public class AppApplyMapperTest
        extends AbstractMapperTest<AppApply, AppApplyMask, AppApplyMapper> {

    @Override
    public DataContext getContext() {
        return TestData.getDefaultContext();
    }

    @Override
    public AppApply buildSample() {
        return AppApplySamples.build();
    }

}
