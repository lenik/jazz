package net.bodz.lily.model.mx.base;

import net.bodz.bas.c.java.util.Dates;

import net.bodz.lily.model.base.security.User;

public class Liker {

    User user;
    long time;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return user + "/" + Dates.SYS_DATETIME.format(time);
    }

}
