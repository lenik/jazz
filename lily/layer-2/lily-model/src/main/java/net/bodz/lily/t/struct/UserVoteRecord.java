package net.bodz.lily.t.struct;

import java.io.IOException;

import net.bodz.bas.c.java.util.Dates;
import net.bodz.bas.err.ParseException;
import net.bodz.bas.fmt.json.IJsonOut;
import net.bodz.bas.fmt.json.JsonObject;
import net.bodz.lily.security.User;

public class UserVoteRecord
        extends AbstractUserClickRecord {

    private User user;
    private int value;

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

    @Override
    public void readObject(JsonObject o)
            throws ParseException {
        super.readObject(o);
        user = o.readInto("user", user, new User());
        value = o.getInt("value", value);
    }

    @Override
    public void writeObject(IJsonOut out)
            throws IOException {
        super.writeObject(out);
        out.entryNotNull("user", user);
        out.entry("value", value);
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
