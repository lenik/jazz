package net.bodz.lily.concrete.util;

import java.io.IOException;

import net.bodz.bas.err.ParseException;
import net.bodz.bas.fmt.json.IJsonOut;
import net.bodz.bas.fmt.json.JsonFormOptions;
import net.bodz.bas.json.JsonObject;
import net.bodz.lily.schema.account.User;

public class UserFlagRecord
        extends AbstractUserClickRecord {

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

    @Override
    public void jsonIn(JsonObject o, JsonFormOptions opts)
            throws ParseException {
        super.jsonIn(o, opts);
        type = o.getVar(UserFlagType.class, "type");
        user = o.readInto("user", user, User.class);
    }

    @Override
    public void jsonOut(IJsonOut out, JsonFormOptions opts)
            throws IOException {
        super.jsonOut(out, opts);
        out.entry("type", type);
        out.entryNotNull("user", user);
    }

}
