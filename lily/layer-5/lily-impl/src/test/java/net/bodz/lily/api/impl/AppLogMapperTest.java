package net.bodz.lily.api.impl;

import net.bodz.lily.api.Api;
import net.bodz.lily.api.App;
import net.bodz.lily.api.AppLog;
import net.bodz.lily.api.AppLogSamples;
import net.bodz.lily.test.AbstractMapperTest;

public class AppLogMapperTest
        extends AbstractMapperTest<AppLog, AppLogMask, AppLogMapper> {

    @Override
    public AppLog buildSample() {
        App app = tables.pickAny(AppMapper.class, "app");
        Api api = tables.pickAny(ApiMapper.class, "api");
        return AppLogSamples.build(app, api);
    }

}
