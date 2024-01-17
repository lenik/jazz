package net.bodz.lily.reward.dao;

import net.bodz.lily.reward.UserBadge;
import net.bodz.lily.reward.UserBadgeSamples;
import net.bodz.lily.security.dao.UserMapper;
import net.bodz.lily.test.AbstractTableTest;

public class UserBadgeMapperTest
        extends AbstractTableTest<UserBadge, UserBadgeCriteriaBuilder, UserBadgeMapper> {

    @Override
    public UserBadge buildSample()
            throws Exception {
        UserBadgeSamples a = new UserBadgeSamples();
        a.badge = tables.pickAny(BadgeMapper.class, "badge");
        a.user = tables.pickAny(UserMapper.class, "user");
        return a.build();
    }

}
