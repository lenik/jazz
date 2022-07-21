package net.bodz.lily.api.impl;

import net.bodz.bas.db.ctx.DataContext;
import net.bodz.lily.api.AppAccountType;
import net.bodz.lily.api.AppAccountTypeSamples;
import net.bodz.lily.test.AbstractMapperTest;
import net.bodz.violet.TestData;

public class AppAccountTypeMapperTest
        extends AbstractMapperTest<AppAccountType, AppAccountTypeMask, AppAccountTypeMapper> {

    @Override
    public DataContext getContext() {
        return TestData.getDefaultContext();
    }

    @Override
    public AppAccountType buildSample() {
        return AppAccountTypeSamples.build();
    }

}
