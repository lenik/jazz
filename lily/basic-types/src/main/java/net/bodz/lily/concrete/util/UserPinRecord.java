package net.bodz.lily.concrete.util;

import java.io.IOException;

import net.bodz.bas.err.ParseException;
import net.bodz.bas.fmt.json.IJsonOut;
import net.bodz.bas.fmt.json.JsonFormOptions;
import net.bodz.bas.json.JsonObject;
import net.bodz.lily.schema.account.User;

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
    public void jsonIn(JsonObject o, JsonFormOptions opts)
            throws ParseException {
        super.jsonIn(o, opts);

        JsonObject js_user = o.getJsonObject("user");
        if (js_user != null) {
            user = new User();
            int js_userId = js_user.getInt("id", -1);
            if (js_userId != -1)
                user.id(js_userId);
        }
    }

    @Override
    public void jsonOut(IJsonOut out, JsonFormOptions opts)
            throws IOException {
        super.jsonOut(out, opts);
        out.entryNotNull("user", user);
    }

}
