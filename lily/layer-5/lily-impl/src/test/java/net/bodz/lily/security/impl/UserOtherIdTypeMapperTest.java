package net.bodz.lily.security.impl;

import org.junit.Ignore;

import net.bodz.lily.security.UserOtherIdType;
import net.bodz.lily.security.UserOtherIdTypeSamples;
import net.bodz.lily.test.AbstractTableTest;

@Ignore
public class UserOtherIdTypeMapperTest
        extends AbstractTableTest<UserOtherIdType, UserOtherIdTypeMask, UserOtherIdTypeMapper> {

    @Override
    public UserOtherIdType buildSample() {
        return UserOtherIdTypeSamples.build();
    }

}
