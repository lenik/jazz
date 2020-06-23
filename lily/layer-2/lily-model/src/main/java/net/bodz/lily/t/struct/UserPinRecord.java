package net.bodz.lily.t.struct;

import java.io.IOException;

import net.bodz.bas.err.ParseException;
import net.bodz.bas.fmt.json.IJsonOut;
import net.bodz.bas.fmt.json.JsonObject;
import net.bodz.lily.security.User;

public class UserPinRecord
        extends AbstractUserClickRecord {

    private User user;

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

        JsonObject _user = o.getChild("user");
        if (_user != null) {
            user = new User();
            user.setId(_user.getInt("id"));
        }
    }

    @Override
    public void writeObject(IJsonOut out)
            throws IOException {
        super.writeObject(out);
        out.entryNotNull("user", user);
    }

}
