package net.bodz.lily.security.impl;

import net.bodz.bas.db.ctx.DataContext;
import net.bodz.lily.security.UserCategory;
import net.bodz.lily.security.UserCategorySamples;
import net.bodz.lily.test.AbstractMapperTest;
import net.bodz.violet.TestData;

public class UserCategoryMapperTest
        extends AbstractMapperTest<UserCategory, UserCategoryMask, UserCategoryMapper> {

    @Override
    public DataContext getContext() {
        return TestData.getDefaultContext();
    }

    @Override
    public UserCategory buildSample() {
        return UserCategorySamples.build();
    }

}
