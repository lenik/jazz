package net.bodz.lily.schema.reward.dao;

import net.bodz.lily.schema.reward.UserBadge;
import net.bodz.lily.schema.reward.UserBadgeSamples;
import net.bodz.lily.test.AbstractTableTest;

public class UserBadgeMapperTest
        extends AbstractTableTest<UserBadge, UserBadgeMapper> {

    @Override
    public UserBadge buildSample()
            throws Exception {
        UserBadgeSamples a = new UserBadgeSamples();
        return a.buildWired(tables);
    }

}
