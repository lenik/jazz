package net.bodz.lily.model.mx.base;

import net.bodz.bas.c.java.util.Dates;

import net.bodz.lily.model.base.security.User;

public class Voter {

    User user;
    int value;
    long time;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(user);
        if (value > 0)
            sb.append('+');
        else if (value < 0)
            sb.append('=');
        sb.append(value);
        sb.append('/');
        sb.append(Dates.SYS_DATETIME.format(time));
        return sb.toString();
    }

}
