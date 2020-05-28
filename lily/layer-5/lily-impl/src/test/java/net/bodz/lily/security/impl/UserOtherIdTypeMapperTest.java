package net.bodz.lily.security.impl;

import org.junit.Ignore;

import net.bodz.bas.db.ctx.DataContext;
import net.bodz.lily.security.UserOtherIdType;
import net.bodz.lily.security.UserOtherIdTypeSamples;
import net.bodz.lily.test.AbstractMapperTest;
import net.bodz.violet.VioletTests;

@Ignore
public class UserOtherIdTypeMapperTest
        extends AbstractMapperTest<UserOtherIdType, UserOtherIdTypeMask, UserOtherIdTypeMapper> {

    @Override
    public DataContext getContext() {
        return VioletTests.getDefaultContext();
    }

    @Override
    public UserOtherIdType buildSample() {
        return UserOtherIdTypeSamples.build();
    }

}
