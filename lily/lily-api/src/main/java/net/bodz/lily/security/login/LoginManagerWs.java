package net.bodz.lily.security.login;

import java.util.HashMap;
import java.util.Map;

import net.bodz.bas.repr.path.IPathArrival;
import net.bodz.bas.repr.path.IPathDispatchable;
import net.bodz.bas.repr.path.ITokenQueue;
import net.bodz.bas.repr.path.PathArrival;
import net.bodz.bas.repr.path.PathDispatchException;
import net.bodz.bas.site.json.JsonResult;
import net.bodz.bas.t.variant.IVariantMap;
import net.bodz.lily.security.auth.AuthContext;
import net.bodz.lily.security.auth.AuthData;
import net.bodz.lily.security.auth.AuthException;
import net.bodz.lily.security.auth.IAuthModule;
import net.bodz.lily.security.auth.IAuthModuleState;
import net.bodz.lily.security.auth.PamManager;
import net.bodz.lily.security.auth.pam.AuthAction;

public class LoginManagerWs
        implements
            IPathDispatchable {

    Map<String, String> pamAliasMap = new HashMap<>();

    public LoginManagerWs() {
        pamAliasMap.put("login", "passwd");
        pamAliasMap.put("phone", "phone");
        pamAliasMap.put("email", "email");
        // pamAliasMap.put("status", "cookie");
    }

    ILoginManager getLoginManager() {
        return LoginManagers.requireLoginManager();
    }

    int statusCount = 0;

    @Override
    public IPathArrival dispatch(IPathArrival previous, ITokenQueue tokens, IVariantMap<String> q)
            throws PathDispatchException {
        String token = tokens.peek();
        if (token == null)
            return null;

        Object target = null;

        PamManager pamManager = PamManager.fromRequest();

        AuthContext authContext = new AuthContext();
        authContext.setPathInfo(token);
        authContext.setParameters(q);
        authContext.setAutoCreateUser(token.contains("register"));
        if (token.contains("verify"))
            authContext.setAction(AuthAction.VERIFY);

        IAuthModule module = pamManager.getFirstProvider(token, pamAliasMap.get(token));
        if (module != null) {
            LoginResult result = loginModule(module, authContext);
            if (result != null)
                return PathArrival.shift(previous, this, result, tokens);
        }

        switch (token) {
        case "status":
            int countAdd = q.getInt("count", 1);
            statusCount += countAdd;

            LoginToken currentToken = LoginToken.fromRequest();
            Object currentAuthId = null;
            if (currentToken != null) {
                target = currentToken;
                currentAuthId = currentToken.authData.getAuthId();
            }

            AuthContext autoAuthContext = new AuthContext();
            autoAuthContext.setPathInfo(token);
            autoAuthContext.setParameters(q);
            autoAuthContext.setAutoCreateUser(true);

            for (IAuthModule pam : pamManager.getAutos()) {
                IAuthModuleState state = pam.getState(autoAuthContext);
                if (state.isDisabled())
                    continue;
                Object authId = state.getAuthId();
                if (currentAuthId != null && currentAuthId.equals(authId))
                    continue;

                LoginResult loginResult = loginModule(pam, autoAuthContext);
                if (loginResult != null) {
                    target = loginResult.getToken();
                    break;
                }
            }

            if (target == null) {
                JsonResult notLogin = new JsonResult();
                notLogin.setHeader("statusCallCounter", statusCount);
                notLogin.fail("Not logged in.");
                target = notLogin;
            }
            break;

        case "exit":
        case "quit":
        case "logout":
            target = JsonResult.exec(getLoginManager()::logOutAll);
            break;
        }

        if (target != null)
            return PathArrival.shift(previous, this, target, tokens);
        return null;
    }

    LoginResult loginModule(IAuthModule module, AuthContext authContext) {
        AuthData authData;
        try {
            authData = module.authenticate(authContext);
            if (authData != null) {
                LoginResult result = new LoginResult();

                Map<String, ?> headers = authContext.getResults();
                for (String k : headers.keySet()) {
                    result.setHeader(k, headers.get(k));
                }

                if (authData.isCompleted()) {
                    ILoginManager loginManager = getLoginManager();
                    LoginToken token = loginManager.logIn(authData);
                    result.setToken(token);
                }
                return result.succeed();
            }
        } catch (AuthException e) {
            return new LoginResult().fail(e, "failed to authenticate: " + e.getMessage());
        }
        return null;
    }

}
