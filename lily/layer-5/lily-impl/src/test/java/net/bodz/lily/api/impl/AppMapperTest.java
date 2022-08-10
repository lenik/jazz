package net.bodz.lily.api.impl;

import net.bodz.lily.api.App;
import net.bodz.lily.api.AppApply;
import net.bodz.lily.api.AppSamples;
import net.bodz.lily.test.AbstractMapperTest;

public class AppMapperTest
        extends AbstractMapperTest<App, AppMask, AppMapper> {

    @Override
    public App buildSample() {
        AppApply apply = tables.pickAny(AppApplyMapper.class, "apply");
        return AppSamples.build(apply);
    }

}
