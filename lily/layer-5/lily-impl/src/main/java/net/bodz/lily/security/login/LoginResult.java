package net.bodz.lily.security.login;

import java.io.IOException;

import net.bodz.bas.err.NotImplementedException;
import net.bodz.bas.err.ParseException;
import net.bodz.bas.fmt.json.IJsonOut;
import net.bodz.bas.fmt.json.IJsonSerializable;
import net.bodz.bas.fmt.json.JsonObject;
import net.bodz.bas.site.json.AbstractJsonResponse;

public class LoginResult
        extends AbstractJsonResponse<LoginResult>
        implements IJsonSerializable {

    String serverChallenge;
    LoginToken token;

    public LoginResult() {
    }

    public LoginResult(AbstractJsonResponse<?> o) {
        super(o);
    }

    public LoginResult(AbstractJsonResponse<?> o, boolean shallowCopy) {
        super(o, shallowCopy);
    }

    public String getServerChallenge() {
        return serverChallenge;
    }

    public void setServerChallenge(String serverChallenge) {
        this.serverChallenge = serverChallenge;
    }

    public LoginToken getToken() {
        return token;
    }

    public void setToken(LoginToken token) {
        this.token = token;
    }

    @Override
    public void readObject(JsonObject o)
            throws ParseException {
        throw new NotImplementedException();
    }

    @Override
    public void writeObject(IJsonOut out)
            throws IOException {
        super.writeObject(out);
        out.entry("sc", serverChallenge);
        if (token != null)
            out.entry("token", token);
    }

}
