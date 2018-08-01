package net.bodz.lily.model.mixin;

import net.bodz.lily.security.User;

public class UserFlagRecord
        extends AbstractUserClickRecord {

    private static final long serialVersionUID = 1L;

    private UserFlagType type;
    private User user;

    public UserFlagType getType() {
        return type;
    }

    public void setType(UserFlagType type) {
        this.type = type;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

}
