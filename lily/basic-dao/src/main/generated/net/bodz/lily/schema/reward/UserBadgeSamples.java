package net.bodz.lily.schema.reward;

import net.bodz.lily.schema.account.User;
import net.bodz.lily.schema.account.dao.UserMapper;
import net.bodz.lily.schema.reward.dao.BadgeMapper;
import net.bodz.lily.test.TestSampleBuilder;
import net.bodz.lily.util.IRandomPicker;

public class UserBadgeSamples
        extends TestSampleBuilder {

    public Badge badge;
    public User user;

    @Override
    public UserBadge build()
            throws Exception {
        UserBadge a = new UserBadge();
        a.setBadge(badge);
        a.setUser(user);
        return a;
    }

    @Override
    public UserBadgeSamples wireAny(IRandomPicker picker) {
        this.badge = picker.pickAny(BadgeMapper.class, "badge");
        this.user = picker.pickAny(UserMapper.class, "user");
        return this;
    }

    @Override
    public UserBadge buildWired(IRandomPicker picker) throws Exception {
        return wireAny(picker).build();
    }

}
