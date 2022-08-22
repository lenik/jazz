package net.bodz.lily.api.impl;

import net.bodz.lily.api.Api;
import net.bodz.lily.api.App;
import net.bodz.lily.api.AppAccount;
import net.bodz.lily.api.AppAccountSamples;
import net.bodz.lily.api.AppAccountType;
import net.bodz.lily.test.AbstractTableTest;

public class AppAccountMapperTest
        extends AbstractTableTest<AppAccount, AppAccountMask, AppAccountMapper> {

    @Override
    public AppAccount buildSample() {
        App app = tables.pickAny(AppMapper.class, "app");
        Api api = tables.pickAny(ApiMapper.class, "api");
        AppAccountType type = tables.pickAny(AppAccountTypeMapper.class, "appacntcat");
        return AppAccountSamples.build(app, api, type);
    }

}
