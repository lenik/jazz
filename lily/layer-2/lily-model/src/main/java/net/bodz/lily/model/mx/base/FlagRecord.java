package net.bodz.lily.model.mx.base;

import net.bodz.lily.model.base.security.User;

public class FlagRecord
        extends UserClickRecord {

    private static final long serialVersionUID = 1L;

    private FlagType type;
    private User user;

    public FlagType getType() {
        return type;
    }

    public void setType(FlagType type) {
        this.type = type;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

}
