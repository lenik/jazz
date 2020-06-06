package net.bodz.lily.security.login;

import java.util.ArrayList;
import java.util.List;

import net.bodz.bas.db.ctx.DataContext;
import net.bodz.bas.repr.path.IPathArrival;
import net.bodz.bas.repr.path.IPathDispatchable;
import net.bodz.bas.repr.path.ITokenQueue;
import net.bodz.bas.repr.path.PathArrival;
import net.bodz.bas.repr.path.PathDispatchException;
import net.bodz.bas.site.ajax.AjaxResult;
import net.bodz.bas.site.json.JsonResponse;
import net.bodz.bas.t.variant.IVariantMap;
import net.bodz.lily.security.User;

public class LoginManager
        extends LoginTokenManager
        implements IPathDispatchable {

    // long timeout = 3600_000;
    DataContext dataContext;

    List<ILoginResolver> loginResolver = new ArrayList<>();
    List<ILoginListener> loginListeners = new ArrayList<>();

    public LoginManager(DataContext dataContext) {
        if (dataContext == null)
            throw new NullPointerException("dataContext");
        this.dataContext = dataContext;
    }

    int saltTimeout = 10_000; // 10 seconds
    SaltGenerator saltgen = new SaltGenerator(saltTimeout);

    @Override
    public IPathArrival dispatch(IPathArrival previous, ITokenQueue tokens, IVariantMap<String> q)
            throws PathDispatchException {
        String token = tokens.peek();
        if (token == null)
            return null;

        Object target = null;
        AjaxResult result = new AjaxResult();

        switch (token) {
        case "init":
            target = initiate(q);
            break;

        case "exit":
        case "quit":
            target = logout();
            break;
        }

        if (target != null)
            return PathArrival.shift(previous, target, tokens);
        return null;
    }

    LoginResult initiate(IVariantMap<String> q) {
        LoginResult result = new LoginResult();
        String clientResp = q.getString("cr");
        if (clientResp == null) { // pass 1:
            String serverChallenge = saltgen.compute();
            result.setServerChallenge(serverChallenge);
            return result;
        }

        // pass 2:
        String secret = "";
        SecretChallenger secretChallenger = new SecretChallenger(saltgen, secret, saltTimeout);

        int ridx = secretChallenger.rcheck(clientResp);
        if (ridx == -1)
            return result.fail("server challenge failed.");

        result.succeed();
        return result;
    }

    JsonResponse logout() {
        JsonResponse r = new JsonResponse();
        LoginToken token = LoginToken.fromSession();
        if (token != null) {
            User user = token.getUser();
            for (ILoginListener listener : loginListeners)
                try {
                    listener.logout(user);
                } catch (Exception e) {
                    return r.fail(e, "Can't logged out %s: %s", user.toString(), listener + ": " + e.getMessage());
                }
            LoginToken.clearSession();
        }
        return r.succeed();
    }

}
