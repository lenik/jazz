package net.bodz.lily.security.login;

import java.io.IOException;

import net.bodz.bas.err.FormatException;
import net.bodz.bas.err.ParseException;
import net.bodz.bas.fmt.json.IJsonForm;
import net.bodz.bas.fmt.json.IJsonOut;
import net.bodz.bas.fmt.json.JsonFormOptions;
import net.bodz.bas.json.JsonObject;
import net.bodz.bas.site.json.AbstractJsonResponse;

public class LoginResult
        extends AbstractJsonResponse<LoginResult>
        implements
            IJsonForm {

    String serverChallenge;
    public LoginToken token;

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
    protected boolean readRootEntry(JsonObject o, String key, JsonFormOptions opts)
            throws ParseException {
        switch (key) {
        case "sc":
            serverChallenge = o.getString("sc");
            return true;
        case "token":
            JsonObject tokenNode = o.getJsonObject("token");
            if (tokenNode != null) {
                this.token = LoginToken.create();
                token.jsonIn(tokenNode, opts);
            }
            return true;
        }
        return super.readRootEntry(o, key, opts);
    }

    @Override
    public void jsonOut(IJsonOut out, JsonFormOptions opts)
            throws IOException, FormatException {
        super.jsonOut(out, opts);
        out.entry("sc", serverChallenge);
        if (token != null)
            out.entry("token", token);
    }

}
