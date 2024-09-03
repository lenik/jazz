package net.bodz.lily.security.auth;

import java.util.LinkedHashMap;
import java.util.Map;

import net.bodz.bas.crypto.trans.ISignatureChecker;
import net.bodz.bas.t.variant.IVariantMap;
import net.bodz.lily.security.auth.pam.AuthAction;

public class AuthContext
        implements
            IAuthContext {

    // options
    boolean autoCreateUser;
    ISignatureChecker signatureChecker;

    // input
    String pathInfo;
    AuthAction action = AuthAction.LOGIN;
    IVariantMap<String> parameters;

    // String eClientResp;

    // output
    Map<String, Object> results = new LinkedHashMap<>();

    public PamManager getPamManager() {
        return PamManager.fromRequest();
    }

    public boolean isAutoCreateUser() {
        return autoCreateUser;
    }

    public void setAutoCreateUser(boolean autoCreateUser) {
        this.autoCreateUser = autoCreateUser;
    }

    public String getPathInfo() {
        return pathInfo;
    }

    public void setPathInfo(String pathInfo) {
        this.pathInfo = pathInfo;
    }

    public AuthAction getAction() {
        return action;
    }

    public void setAction(AuthAction action) {
        this.action = action;
    }

    public IVariantMap<String> getParameters() {
        return parameters;
    }

    public void setParameters(IVariantMap<String> parameters) {
        this.parameters = parameters;
    }

    public Map<String, Object> getResults() {
        return results;
    }

    @Override
    public void addResult(String name, Object value) {
        results.put(name, value);
    }

    public void setHeader(String name, Object value) {
        addResult(name, value);
    }

}
