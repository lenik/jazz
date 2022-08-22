package net.bodz.lily.security.impl;

import net.bodz.lily.security.UserCategory;
import net.bodz.lily.security.UserCategorySamples;
import net.bodz.lily.test.AbstractTableTest;

public class UserCategoryMapperTest
        extends AbstractTableTest<UserCategory, UserCategoryMask, UserCategoryMapper> {

    @Override
    public UserCategory buildSample() {
        return UserCategorySamples.build();
    }

}
