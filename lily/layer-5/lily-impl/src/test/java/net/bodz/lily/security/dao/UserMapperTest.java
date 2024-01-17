package net.bodz.lily.security.dao;

import net.bodz.lily.security.User;
import net.bodz.lily.security.UserSamples;
import net.bodz.lily.test.AbstractTableTest;

public class UserMapperTest
        extends AbstractTableTest<User, UserCriteriaBuilder, UserMapper> {

    @Override
    public User buildSample()
            throws Exception {
        UserSamples a = new UserSamples();
        a.primaryGroup = tables.pickAny(GroupMapper.class, "group");
        a.referer = tables.pickAny(UserMapper.class, "user");
        a.type = tables.pickAny(UserTypeMapper.class, "usertype");
        return a.build();
    }

}
