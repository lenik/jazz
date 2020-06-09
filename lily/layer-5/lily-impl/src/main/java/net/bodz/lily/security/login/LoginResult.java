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
    String expectedClientResp; // for debug
    LoginToken token;

    public String getServerChallenge() {
        return serverChallenge;
    }

    public void setServerChallenge(String serverChallenge) {
        this.serverChallenge = serverChallenge;
    }

    public String getExpectedClientResp() {
        return expectedClientResp;
    }

    public void setExpectedClientResp(String expectedClientResp) {
        this.expectedClientResp = expectedClientResp;
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
        out.entry("sc", serverChallenge);
        if (expectedClientResp != null)
            out.entry("ecr", expectedClientResp);
        if (token != null)
            out.entry("token", token);
    }

}
