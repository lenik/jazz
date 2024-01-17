package net.bodz.lily.reward;

import net.bodz.lily.reward.dao.BadgeMapper;
import net.bodz.lily.security.User;
import net.bodz.lily.security.dao.UserMapper;
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
        a.setId(178419693);
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
