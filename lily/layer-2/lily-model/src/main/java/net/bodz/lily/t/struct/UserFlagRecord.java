package net.bodz.lily.t.struct;

import java.io.IOException;

import net.bodz.bas.err.ParseException;
import net.bodz.bas.fmt.json.IJsonOut;
import net.bodz.bas.json.JsonObject;
import net.bodz.lily.security.User;

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
    public void readObject(JsonObject o)
            throws ParseException {
        super.readObject(o);
        type = o.getVar(UserFlagType.class, "type");
        user = o.readInto("user", user, new User());
    }

    @Override
    public void writeObject(IJsonOut out)
            throws IOException {
        super.writeObject(out);
        out.entry("type", type);
        out.entryNotNull("user", user);
    }

}
