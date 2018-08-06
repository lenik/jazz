package net.bodz.lily.security.impl;

import org.junit.Ignore;

import net.bodz.bas.db.ctx.DataContext;
import net.bodz.lily.security.UserIdType;
import net.bodz.lily.security.UserIdTypeSamples;
import net.bodz.lily.test.AbstractMapperTest;
import net.bodz.violet.VioletTests;

@Ignore
public class UserIdTypeMapperTest
        extends AbstractMapperTest<UserIdType, UserIdTypeMask, UserIdTypeMapper> {

    @Override
    public DataContext getContext() {
        return VioletTests.getDefaultContext();
    }

    @Override
    public UserIdType buildSample() {
        return UserIdTypeSamples.build();
    }

}
