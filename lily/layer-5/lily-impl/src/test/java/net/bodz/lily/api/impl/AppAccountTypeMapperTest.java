package net.bodz.lily.api.impl;

import net.bodz.lily.api.AppAccountType;
import net.bodz.lily.api.AppAccountTypeSamples;
import net.bodz.lily.test.AbstractTableTest;

public class AppAccountTypeMapperTest
        extends AbstractTableTest<AppAccountType, AppAccountTypeMask, AppAccountTypeMapper> {

    @Override
    public AppAccountType buildSample() {
        return AppAccountTypeSamples.build();
    }

}
