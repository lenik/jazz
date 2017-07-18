package net.bodz.lily.model.mixin;

import net.bodz.bas.c.java.util.Dates;
import net.bodz.lily.model.base.security.User;

public class UserPinRecord
        extends AbstractUserClickRecord {

    private static final long serialVersionUID = 1L;

    private User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return user + "/" + Dates.SYS_DATETIME.format(time);
    }

}
