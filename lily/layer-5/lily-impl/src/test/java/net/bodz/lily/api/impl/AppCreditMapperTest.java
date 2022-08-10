package net.bodz.lily.api.impl;

import net.bodz.lily.api.Api;
import net.bodz.lily.api.App;
import net.bodz.lily.api.AppCredit;
import net.bodz.lily.api.AppCreditSamples;
import net.bodz.lily.test.AbstractMapperTest;

public class AppCreditMapperTest
        extends AbstractMapperTest<AppCredit, AppCreditMask, AppCreditMapper> {

    @Override
    public AppCredit buildSample() {
        App app = tables.pickAny(AppMapper.class, "app");
        Api api = tables.pickAny(ApiMapper.class, "api");
        return AppCreditSamples.build(app, api);
    }

}
