package net.bodz.lily.concrete.util;

import java.io.IOException;

import net.bodz.bas.c.java.time.DateTimes;
import net.bodz.bas.err.ParseException;
import net.bodz.bas.fmt.json.IJsonOut;
import net.bodz.bas.fmt.json.JsonFormOptions;
import net.bodz.bas.json.JsonObject;
import net.bodz.bas.meta.decl.NotNull;
import net.bodz.lily.schema.account.User;

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
    public void jsonIn(@NotNull JsonObject o, JsonFormOptions opts)
            throws ParseException {
        super.jsonIn(o, opts);
        user = o.readInto("user", user, User.class);
        value = o.getInt("value", value);
    }

    @Override
    public void jsonOut(IJsonOut out, JsonFormOptions opts)
            throws IOException {
        super.jsonOut(out, opts);
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
        sb.append(DateTimes.ISO_ZONED_DATE_TIME.format(time));
        return sb.toString();
    }

}
