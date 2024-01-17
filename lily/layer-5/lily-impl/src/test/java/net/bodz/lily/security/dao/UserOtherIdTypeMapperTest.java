package net.bodz.lily.security.dao;

import net.bodz.lily.security.UserOtherIdType;
import net.bodz.lily.security.UserOtherIdTypeSamples;
import net.bodz.lily.test.AbstractTableTest;

public class UserOtherIdTypeMapperTest
        extends AbstractTableTest<UserOtherIdType, UserOtherIdTypeCriteriaBuilder, UserOtherIdTypeMapper> {

    @Override
    public UserOtherIdType buildSample()
            throws Exception {
        UserOtherIdTypeSamples a = new UserOtherIdTypeSamples();
        return a.build();
    }

}
