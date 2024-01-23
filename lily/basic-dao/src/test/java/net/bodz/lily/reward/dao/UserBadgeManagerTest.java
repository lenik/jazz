package net.bodz.lily.reward.dao;

import net.bodz.lily.reward.UserBadge;
import net.bodz.lily.reward.UserBadgeSamples;
import net.bodz.lily.test.AbstractManagerTest;

public class UserBadgeManagerTest
        extends AbstractManagerTest<UserBadge, UserBadgeMapper, UserBadgeManager> {

    @Override
    public UserBadge buildSample()
            throws Exception {
        UserBadgeSamples a = new UserBadgeSamples();
        return a.buildWired(tables);
    }

}
