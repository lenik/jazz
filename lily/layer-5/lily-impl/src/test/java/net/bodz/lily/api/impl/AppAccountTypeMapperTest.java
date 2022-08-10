package net.bodz.lily.api.impl;

import net.bodz.lily.api.AppAccountType;
import net.bodz.lily.api.AppAccountTypeSamples;
import net.bodz.lily.test.AbstractMapperTest;

public class AppAccountTypeMapperTest
        extends AbstractMapperTest<AppAccountType, AppAccountTypeMask, AppAccountTypeMapper> {

    @Override
    public AppAccountType buildSample() {
        return AppAccountTypeSamples.build();
    }

}
